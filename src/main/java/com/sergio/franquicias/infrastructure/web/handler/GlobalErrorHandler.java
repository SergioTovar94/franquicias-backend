package com.sergio.franquicias.infrastructure.web.handler;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleValidation(WebExchangeBindException ex) {
        String errores = ex.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Error de validación");
        return buildResponse(
                400,
                "Bad Request",
                errores);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleInputError(ServerWebInputException ex) {
        Throwable cause = ex.getCause();
        String msg = (cause != null)
                ? cause.getMessage()
                : ex.getMessage();

        return buildResponse(
                400,
                "Bad Request",
                "Error de entrada: " + msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleRuntime(RuntimeException ex) {
        return buildResponse(
                500,
                "Internal Server Error",
                ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleBusinessError(
            IllegalArgumentException ex) {

        return buildResponse(
                400,
                "Bad Request",
                ex.getMessage());
    }

    private Mono<ResponseEntity<Map<String, Object>>> buildResponse(
            int status,
            String error,
            String message) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status);
        body.put("error", error);
        body.put("message", message);

        return Mono.just(
                ResponseEntity
                        .status(status)
                        .body(body));
    }
}
