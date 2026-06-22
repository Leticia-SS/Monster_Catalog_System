package com.example.monsterservice.controller;


import com.example.monsterservice.dto.MonsterCreateDto;
import com.example.monsterservice.dto.MonsterUpdateDto;
import com.example.monsterservice.model.Monster;
import com.example.monsterservice.model.enums.MonsterStatusEnum;
import com.example.monsterservice.service.MonsterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/monsters")
public class MonsterController {
    private final MonsterService monsterService;
    private static final Logger logger = LoggerFactory.getLogger("REQUEST_LOGGER");

    @GetMapping
    public ResponseEntity<List<Monster>> listAllMonsters() {
        logger.info("GET /monsters = listando todos os monstros");
        return ResponseEntity.ok(monsterService.getAllMonsters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMonsterById(@PathVariable Long id) {
        logger.info("GET /monsters/{} = listando monstro por Id", id);
        return monsterService.getMonsterById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: monstro não encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> addMonster(@RequestBody MonsterCreateDto dto) {
        logger.info("POST /monsters = cadastrando novo monstro: {}", dto.getName());

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(monsterService.addMonster(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMonster(@PathVariable Long id, @RequestBody MonsterUpdateDto dto) {
        logger.info("PUT /monsters/{} = atualizando monstro", id);
        return monsterService.updateMonster(id, dto).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: monstro não encontrado"));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Monster>> getMonsterByCategory(@PathVariable Long categoryId) {
        logger.info("GET /monsters/category/{} = buscando monstros por categoria", categoryId);
        return ResponseEntity.ok(monsterService.getMonstersByCategory(categoryId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getMonsterByStatus(@PathVariable String status) {
        logger.info("GET /monsters/status/{} = buscando monstros por status", status);
        try {
            MonsterStatusEnum statusEnum = MonsterStatusEnum.valueOf(status.toUpperCase());
            return ResponseEntity.ok(monsterService.getMonstersByStatus(statusEnum));
        } catch (IllegalArgumentException er) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: status invalido. Valores possíveis: COMMON, RARE e EXTINCT");
        }
    }




}
