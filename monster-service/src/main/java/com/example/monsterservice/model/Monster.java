package com.example.monsterservice.model;


import com.example.monsterservice.model.enums.MonsterStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "monsters")
public class Monster {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private Long regionId;
    @Column(name = "threat_level")
    private Integer threatLevel;
    @Enumerated(EnumType.STRING)
    private MonsterStatusEnum status;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String abilities;
    private Integer sightingCount = 0;

}
