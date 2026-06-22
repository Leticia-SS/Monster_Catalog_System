package com.example.monsterservice.dto;

import lombok.Data;

@Data
public class MonsterCreateDto {
    private String name;
    private String description;
    private Long categoryId;
}
