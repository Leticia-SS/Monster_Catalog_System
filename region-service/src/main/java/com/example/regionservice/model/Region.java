package com.example.regionservice.model;

import com.example.regionservice.model.enums.EnvironmentTypeRnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "regions", schema = "region")
public class Region {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "environment_type")
    private EnvironmentTypeRnum environmentType;
    @Column(name = "danger_level")
    @Min(1)@Max(10)
    private Integer dangerLevel;
    @Column(columnDefinition = "TEXT")
    private String description;
}
