package com.example.catalog.Monster.Catalog.System.monster.model;

import com.example.catalog.Monster.Catalog.System.monster.model.enums.MonsterStatusEnum;
import com.example.catalog.Monster.Catalog.System.region.model.Region;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "monsters", schema = "monster")
public class Monster {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    @Column(name = "threat_level")
    private Integer threatLevel;
    @Enumerated(EnumType.STRING)
    private MonsterStatusEnum status;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String abilities;

}
