package com.example.regionservice.exception;

public class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(Long id) {
        super("Região não encontrada para id: " + id);
    }
}