package com.example.catalog.Monster.Catalog.System.monster.repository;

import com.example.catalog.Monster.Catalog.System.monster.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMonsterRepository extends JpaRepository<Monster, Long> {
}
