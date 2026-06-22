package com.example.monsterservice.event;

import java.time.LocalDate;

public record SightingRegisteredEvent(
        String eventId,
        Long monsterId,
        Integer quantity,
        LocalDate sightingDate
) {}