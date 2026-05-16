package com.example.catalog.Monster.Catalog.System.region.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sightings", schema = "region")
public class Sighting {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "monster_id", nullable = false)
    private Long monsterId;
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    @Column(name = "sighting_date")
    private LocalDate sightingDate;
    private Integer quantity;



}
