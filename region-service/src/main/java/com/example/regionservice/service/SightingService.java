package com.example.regionservice.service;

import com.example.regionservice.dto.SightingRequestDto;
import com.example.regionservice.event.SightingRegisteredEvent;
import com.example.regionservice.kafka.SightingEventProducer;
import com.example.regionservice.model.Region;
import com.example.regionservice.model.Sighting;
import com.example.regionservice.repository.IRegionRepository;
import com.example.regionservice.repository.ISightingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import com.example.regionservice.client.MonsterClient;

@Service
@AllArgsConstructor
public class SightingService {
    private final ISightingRepository sightingRepository;
    private final IRegionRepository regionRepository;
    private final SightingEventProducer sightingEventProducer;
    //private final MonsterClient monsterClient;

    public List<Sighting> getAllByRegion(Long regionId) {
        return sightingRepository.findByRegionId(regionId);
    }

    public List<Sighting> getAllByMonster(Long monsterId) {
        return sightingRepository.findByMonsterId(monsterId);
    }

    //@CircuitBreaker(name = "monsterService", fallbackMethod = "fallbackRegisterSighting")
    public Sighting registerSighting(SightingRequestDto dto) {
        //monsterClient.validateMonster(dto.getMonsterId());

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Erro: região não encontrada"));

        Sighting sighting = new Sighting();
        sighting.setMonsterId(dto.getMonsterId());
        sighting.setRegion(region);
        sighting.setSightingDate(dto.getSightingDate());
        sighting.setQuantity(dto.getQuantity());
        sighting.setNotes(dto.getNotes());

        Sighting saved = sightingRepository.save(sighting);

        //producer.sendSightingRegistered(
        //                new SightingRegisteredEvent(
        //                        saved.getId().toString(),
        //                        saved.getMonsterId(),
        //                        saved.getQuantity(),
        //                        saved.getSightingDate()
        //                )
        //        );

        SightingRegisteredEvent event = new SightingRegisteredEvent(
                saved.getId().toString(),
                saved.getMonsterId(),
                saved.getQuantity(),
                saved.getSightingDate()
        );
        sightingEventProducer.sendSightingRegistered(event);

        return saved;
    }

//    public Sighting fallbackRegisterSighting(SightingRequestDto dto, Throwable ex) {
//
//        System.out.println("Circuit Breaker ABERTO - fallback ativado");
//
//        Sighting sighting = new Sighting();
//        sighting.setMonsterId(dto.getMonsterId());
//        sighting.setSightingDate(dto.getSightingDate());
//        sighting.setQuantity(dto.getQuantity());
//        sighting.setNotes(dto.getNotes());
//
//        return sightingRepository.save(sighting);
//    }
}

//O uso de Circuit Breaker foi considerado, porem descartado neste cenário para evitar complexidade desnecessária, já que não existem chamadas síncronas entre os serviços.
//O Circuit Breaker seria reintroduzido caso houvesse validação síncrona do monster-service via HTTP, onde falhas de rede poderiam impactar diretamente a criação de avistamentos.
//O Circuit Breaker não foi aplicado no fluxo atual porque a comunicação entre region-service e monster-service é totalmente assíncrona via Kafka.
//O Kafka já atua como mecanismo de resiliência, desacoplando os serviços e garantindo tolerância a falhas por meio de buffer de mensagens e retry no consumer.