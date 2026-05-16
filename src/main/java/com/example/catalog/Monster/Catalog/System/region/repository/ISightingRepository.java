package com.example.catalog.Monster.Catalog.System.region.repository;

import com.example.catalog.Monster.Catalog.System.region.model.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISightingRepository extends JpaRepository<Sighting, Long> {
    List<Sighting> findByRegionId(Long regionId);
    List<Sighting> findByMonsterId(Long monsterId);


}
