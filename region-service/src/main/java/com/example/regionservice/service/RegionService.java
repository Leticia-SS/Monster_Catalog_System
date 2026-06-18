package com.example.regionservice.service;

import com.example.regionservice.model.Region;
import com.example.regionservice.repository.IRegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegionService {
    private final IRegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    public Region addRegion(Region region) {
        return regionRepository.save(region);
    }

    public Optional<Region> updateRegion(Long id, Region newRegion) {
        return regionRepository.findById(id).map(existing -> {
            newRegion.setId(id);
            return regionRepository.save(newRegion);
        });
    }


}
