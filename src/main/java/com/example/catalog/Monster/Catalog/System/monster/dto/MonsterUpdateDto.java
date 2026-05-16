package com.example.catalog.Monster.Catalog.System.monster.dto;

import com.example.catalog.Monster.Catalog.System.monster.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonsterUpdateDto {
    private String name;
    private Category category;
    private Long regionId;
    private Integer threatLevel;
    private String description;
    private String abilities;
}
