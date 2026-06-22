package com.example.monsterservice.service;

import com.example.monsterservice.dto.MonsterCreateDto;
import com.example.monsterservice.dto.MonsterUpdateDto;
import com.example.monsterservice.model.Category;
import com.example.monsterservice.model.Monster;
import com.example.monsterservice.model.enums.MonsterStatusEnum;
import com.example.monsterservice.repository.ICategoryRepository;
import com.example.monsterservice.repository.IMonsterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MonsterService {
    private final IMonsterRepository monsterRepository;
    private final ICategoryRepository categoryRepository;

    public List<Monster> getAllMonsters() {
        return monsterRepository.findAll();
    }

    public Optional<Monster> getMonsterById(Long id) {
        return monsterRepository.findById(id);
    }

    public Monster addMonster(MonsterCreateDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Monster monster = new Monster();
        monster.setName(dto.getName());
        monster.setDescription(dto.getDescription());
        monster.setCategory(category);
        monster.setSightingCount(0);
        monster.setStatus(MonsterStatusEnum.EXTINCT);

        return monsterRepository.save(monster);
    }

    public Optional<Monster> updateMonster(Long id, MonsterUpdateDto dto) {
        return monsterRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setRegionId(dto.getRegionId());
            existing.setThreatLevel(dto.getThreatLevel());
            existing.setDescription(dto.getDescription());
            existing.setAbilities(dto.getAbilities());
            return monsterRepository.save(existing);
        });
    }

    public List<Monster> getMonstersByCategory(Long categoryId) {
        return monsterRepository.findByCategoryId(categoryId);
    }

    public List<Monster> getMonstersByStatus(MonsterStatusEnum status) {
        return monsterRepository.findByStatus(status);
    }

    public void incrementSighting(Long monsterId) {
        monsterRepository.findById(monsterId).ifPresent(monster -> {
            monster.setSightingCount(monster.getSightingCount() + 1);
            monster.setStatus(calculateStatus(monster.getSightingCount()));
            monsterRepository.save(monster);
        });
    }

    private MonsterStatusEnum calculateStatus(int count) {
        if (count == 0) return MonsterStatusEnum.EXTINCT;
        if (count <= 5) return MonsterStatusEnum.RARE;
        return MonsterStatusEnum.COMMON;
    }




}
