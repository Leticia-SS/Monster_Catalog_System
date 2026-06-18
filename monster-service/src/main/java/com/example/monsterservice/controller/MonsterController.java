package com.example.monsterservice.controller;


import com.example.monsterservice.dto.MonsterUpdateDto;
import com.example.monsterservice.model.Monster;
import com.example.monsterservice.model.enums.MonsterStatusEnum;
import com.example.monsterservice.service.MonsterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/monsters")
public class MonsterController {
    private final MonsterService monsterService;

    @GetMapping
    public ResponseEntity<List<Monster>> listAllMonsters() {
        return ResponseEntity.ok(monsterService.getAllMonsters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMonsterById(@PathVariable Long id) {
        return monsterService.getMonsterById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: monstro não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Monster> addMonster(@RequestBody Monster monster) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monsterService.addMonster(monster));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMonster(@PathVariable Long id, @RequestBody MonsterUpdateDto dto) {
        return monsterService.updateMonster(id, dto).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: monstro não encontrado"));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Monster>> getMonsterByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(monsterService.getMonstersByCategory(categoryId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getMonsterByStatus(@PathVariable String status) {
        try {
            MonsterStatusEnum statusEnum = MonsterStatusEnum.valueOf(status.toUpperCase());
            return ResponseEntity.ok(monsterService.getMonstersByStatus(statusEnum));
        } catch (IllegalArgumentException er) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: status invpalido. Valores possíveis: COMMON, RARE e EXTINCT");
        }
    }




}
