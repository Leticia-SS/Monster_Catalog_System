package com.example.regionservice.exception;

public class InvalidSightingException extends RuntimeException {

    public InvalidSightingException(String message) {
        super(message);
    }
}