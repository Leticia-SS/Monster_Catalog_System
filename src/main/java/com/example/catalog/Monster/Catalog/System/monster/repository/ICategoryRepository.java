package com.example.catalog.Monster.Catalog.System.monster.repository;

import com.example.catalog.Monster.Catalog.System.monster.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
