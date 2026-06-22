package com.example.regionservice.advice;

import com.example.regionservice.exception.InvalidSightingException;
import com.example.regionservice.exception.MonsterNotFoundException;
import com.example.regionservice.exception.RegionNotFoundException;
import com.example.regionservice.payload.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionAdvice {

    private static final Logger logger =
            LoggerFactory.getLogger("REQUEST_LOGGER");

    // REGION NOT FOUND
    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRegionNotFound(RegionNotFoundException ex) {

        logger.error("REGION ERROR | {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "Região não encontrada",
                        ex.getMessage()
                ));
    }

    // MONSTER NOT FOUND
    @ExceptionHandler(MonsterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMonsterNotFound(MonsterNotFoundException ex) {

        logger.error("MONSTER ERROR | {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "Monstro não encontrado",
                        ex.getMessage()
                ));
    }

    // INVALID SIGHTING
    @ExceptionHandler(InvalidSightingException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSighting(InvalidSightingException ex) {

        logger.error("SIGHTING ERROR | {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        "Avistamento inválido",
                        ex.getMessage()
                ));
    }

    // JSON MAL FORMADO
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        "Requisição inválida",
                        "JSON mal formatado ou inválido"
                ));
    }

    // VALIDATION (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        String detalhes = ex.getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList()
                .toString();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        "Erro de validação",
                        detalhes
                ));
    }

    // ROTA NÃO EXISTE
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundRoute(NoResourceFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "Rota não encontrada",
                        "A URL solicitada não existe"
                ));
    }

    // FALLBACK GERAL
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        logger.error("ERRO INTERNO | {}", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "Erro interno",
                        "Ocorreu um erro inesperado no servidor"
                ));
    }
}