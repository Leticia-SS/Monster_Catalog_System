package com.example.monsterservice.controller;

import com.example.monsterservice.model.Category;
import com.example.monsterservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger("REQUEST_LOGGER");


    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("GET /categories = listando todas as categorias");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        logger.info("GET /categories/{} = buscando categoria por id", id);
        return categoryService.getCategoryById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: categoria não enconrtada"));
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        logger.info("POST /categories = cadastrando nova categoria: {}", category.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category));
    }


}
