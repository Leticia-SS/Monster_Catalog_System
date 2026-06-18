package com.example.monsterservice.consumer;

import com.example.monsterservice.service.MonsterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SightingConsumer {
    private final MonsterService monsterService;
    private static final Logger log = LoggerFactory.getLogger(SightingConsumer.class);

    @KafkaListener(topics = "sighting-register", groupId = "monster-group")
    public void consume(String monsterId){
        log.info("Evento recebido do Kafka - monsterId: {}", monsterId);
        monsterService.incrementSighting(Long.parseLong(monsterId));
        log.info("sightingCount atualizado para monstro id: {}", monsterId);
    }
}
