package com.example.regionservice.controller;


import com.example.regionservice.dto.SightingRequestDto;
import com.example.regionservice.model.Sighting;
import com.example.regionservice.service.SightingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sightings")
public class SightingController {

    private final SightingService sightingService;
    private static final Logger logger =
            LoggerFactory.getLogger("REQUEST_LOGGER");

    @PostMapping
    public ResponseEntity<?> registerSighting(@RequestBody SightingRequestDto dto) {
        logger.info(
                "POST /sightings = registrando avistamento | monsterId={} | regionId={}",
                dto.getMonsterId(),
                dto.getRegionId()
        );

        try {
            Sighting saved = sightingService.registerSighting(dto);
            logger.info(
                    "Avistamento registrado com sucesso | sightingId={} | monsterId={}",
                    saved.getId(),
                    saved.getMonsterId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            logger.warn(
                    "Falha ao registrar avistamento | monsterId={} | error={}",
                    dto.getMonsterId(),
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<Sighting>> getByRegion(@PathVariable Long regionId) {
        logger.info("GET /sightings/region/{} = buscando avistamentos por região", regionId);
        List<Sighting> list = sightingService.getAllByRegion(regionId);
        logger.info("Encontrados {} avistamentos para região {}", list.size(), regionId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/monster/{monsterId}")
    public ResponseEntity<List<Sighting>> getByMonster(@PathVariable Long monsterId) {
        logger.info("GET /sightings/monster/{} = buscando avistamentos por monstro", monsterId);
        List<Sighting> list = sightingService.getAllByMonster(monsterId);
        logger.info("Encontrados {} avistamentos para monstro {}", list.size(), monsterId);
        return ResponseEntity.ok(list);
    }
}