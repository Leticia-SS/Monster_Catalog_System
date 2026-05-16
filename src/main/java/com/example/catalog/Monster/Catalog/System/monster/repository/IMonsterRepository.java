package com.example.catalog.Monster.Catalog.System.monster.repository;

import com.example.catalog.Monster.Catalog.System.monster.model.Monster;
import com.example.catalog.Monster.Catalog.System.monster.model.enums.MonsterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMonsterRepository extends JpaRepository<Monster, Long> {
    List<Monster> findByCategoryId(Long categoryId);
    List<Monster> findByStatus(MonsterStatusEnum status);


}
