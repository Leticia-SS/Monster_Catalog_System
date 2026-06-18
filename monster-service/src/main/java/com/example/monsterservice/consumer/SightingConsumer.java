package com.example.monsterservice.consumer;

import com.example.monsterservice.service.MonsterService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SightingConsumer {
    private final MonsterService monsterService;

    @KafkaListener(topics = "sighting-register", groupId = "monster-group")
    public void consume(String monsterId){
        monsterService.incrementSighting(Long.parseLong(monsterId));
    }
}
