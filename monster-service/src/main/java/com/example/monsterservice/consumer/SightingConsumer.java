package com.example.monsterservice.consumer;

import com.example.monsterservice.event.SightingRegisteredEvent;
import com.example.monsterservice.service.MonsterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SightingConsumer {
    private final MonsterService monsterService;
    private static final Logger log = LoggerFactory.getLogger(SightingConsumer.class);

    public SightingConsumer(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @KafkaListener(topics = "sighting-register", groupId = "monster-group")
    public void consume(SightingRegisteredEvent event){
        log.info(
                "Evento Kafka recebido | eventId={} | monsterId={} | quantity={} | sightingDate={}",
                event.eventId(),
                event.monsterId(),
                event.quantity(),
                event.sightingDate()
        );

        try {
            monsterService.incrementSighting(event.monsterId());

            log.info(
                    "Evento processado com sucesso | eventId={} | monsterId={}",
                    event.eventId(),
                    event.monsterId()
            );

        } catch (Exception e) {

            log.error(
                    "Erro ao processar evento Kafka | eventId={} | monsterId={}",
                    event.eventId(),
                    event.monsterId(),
                    e
            );

            throw e;
        }
    }
}
