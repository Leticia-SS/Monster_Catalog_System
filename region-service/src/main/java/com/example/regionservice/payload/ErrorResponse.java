package com.example.regionservice.payload;

public record ErrorResponse(
        String mensagem,
        String detalhes
) {}