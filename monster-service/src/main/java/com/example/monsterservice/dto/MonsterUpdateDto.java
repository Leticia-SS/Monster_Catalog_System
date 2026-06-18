package com.example.monsterservice.dto;

import com.example.monsterservice.model.Category;
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
