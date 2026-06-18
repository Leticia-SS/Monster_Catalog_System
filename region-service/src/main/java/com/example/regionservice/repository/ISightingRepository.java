package com.example.regionservice.repository;

import com.example.regionservice.model.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISightingRepository extends JpaRepository<Sighting, Long> {
    List<Sighting> findByRegionId(Long regionId);
    List<Sighting> findByMonsterId(Long monsterId);


}
