package com.example.regionservice.repository;

import com.example.regionservice.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegionRepository extends JpaRepository<Region, Long> {
}
