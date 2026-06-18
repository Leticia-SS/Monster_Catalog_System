package com.example.regionservice.service;

import com.example.regionservice.model.Sighting;
import com.example.regionservice.repository.ISightingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SightingService {
    private final ISightingRepository sightingRepository;

    public List<Sighting> getAllByRegion(Long regionId) {
        return sightingRepository.findByRegionId(regionId);
    }

    public List<Sighting> getAllByMonster(Long monsterId) {
        return sightingRepository.findByMonsterId(monsterId);
    }

    //OBS: Terminar o register que faz chamada para o monster-service
    //public Sighting registerSighting(Sighting sighting) {}



}
