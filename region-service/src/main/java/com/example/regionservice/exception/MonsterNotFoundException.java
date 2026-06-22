package com.example.regionservice.exception;

public class MonsterNotFoundException extends RuntimeException {

    public MonsterNotFoundException(Long monsterId) {
        super("Monstro não encontrado para id: " + monsterId);
    }
}