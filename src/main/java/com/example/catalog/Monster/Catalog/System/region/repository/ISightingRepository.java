package com.example.catalog.Monster.Catalog.System.region.repository;

import com.example.catalog.Monster.Catalog.System.region.model.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISightingRepository extends JpaRepository<Sighting, Long> {
}
