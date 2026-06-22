package com.example.regionservice.kafka;

import com.example.regionservice.event.SightingRegisteredEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SightingEventProducer {
    private static final String TOPIC = "sighting-register";
    private final KafkaTemplate<String, SightingRegisteredEvent> kafkaTemplate;
    private static Logger logger = LoggerFactory.getLogger(SightingEventProducer.class);

    public void sendSightingRegistered(SightingRegisteredEvent event) {
        logger.info(
                "Publicando evento no Kafka = topic: {} | eventId: {} | monsterId: {}",
                TOPIC, event.eventId(), event.monsterId());
        kafkaTemplate.send(TOPIC, event.eventId(), event);
        logger.info("Evento publicado com sucesso no tópico: {} | eventId: {}", TOPIC, event.eventId());
    }
}