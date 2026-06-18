package com.example.monsterservice.service;

import com.example.monsterservice.model.Category;
import com.example.monsterservice.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final ICategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }



}
