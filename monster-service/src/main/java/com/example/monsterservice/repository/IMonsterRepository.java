package com.example.monsterservice.repository;

import com.example.monsterservice.model.Monster;
import com.example.monsterservice.model.enums.MonsterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMonsterRepository extends JpaRepository<Monster, Long> {
    List<Monster> findByCategoryId(Long categoryId);
    List<Monster> findByStatus(MonsterStatusEnum status);


}
