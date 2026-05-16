package com.example.catalog.Monster.Catalog.System.monster.service;

import com.example.catalog.Monster.Catalog.System.monster.dto.MonsterUpdateDto;
import com.example.catalog.Monster.Catalog.System.monster.model.Monster;
import com.example.catalog.Monster.Catalog.System.monster.model.enums.MonsterStatusEnum;
import com.example.catalog.Monster.Catalog.System.monster.repository.IMonsterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MonsterService {
    private final IMonsterRepository monsterRepository;

    public List<Monster> getAllMonsters() {
        return monsterRepository.findAll();
    }

    public Optional<Monster> getMonsterById(Long id) {
        return monsterRepository.findById(id);
    }

    public Monster addMonster(Monster monster) {
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
