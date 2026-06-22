package com.example.regionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SightingRequestDto {
    private Long monsterId;
    private Long regionId;
    private LocalDate sightingDate;
    private Integer quantity;
    private String notes;
}
