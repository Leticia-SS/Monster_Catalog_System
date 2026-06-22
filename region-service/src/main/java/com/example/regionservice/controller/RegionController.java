package com.example.regionservice.controller;

import com.example.regionservice.model.Region;
import com.example.regionservice.service.RegionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;
    private static final Logger logger =
            LoggerFactory.getLogger("REQUEST_LOGGER");

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        logger.info("GET /regions = listando todas as regiões");
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegionById(@PathVariable Long id) {
        logger.info("GET /regions/{} = buscando região por id", id);
        return regionService.getRegionById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Erro: região não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Region> addRegion(@RequestBody Region region) {
        logger.info("POST /regions = criando nova região | name={}", region.getName());
        Region saved = regionService.addRegion(region);
        logger.info("Região criada com sucesso | id={}", saved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable Long id, @RequestBody Region region) {
        logger.info("PUT /regions/{} = atualizando região", id);

        return regionService.updateRegion(id, region)
                .<ResponseEntity<?>>map(updated -> {
                    logger.info("Região atualizada com sucesso | id={}", id);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> {
                    logger.warn("Falha ao atualizar - região não encontrada | id={}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Erro: região não encontrada");
                });
    }
}