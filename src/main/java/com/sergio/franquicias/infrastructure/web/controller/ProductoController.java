package com.sergio.franquicias.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.franquicias.application.dto.ProductoRequest;
import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.usecase.AgregarProductoUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("sucursales/{sucursalId}/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final AgregarProductoUseCase agregarProductoUseCase;

    @PostMapping
    public Mono<ResponseEntity<ProductoResponse>> agregar(
            @PathVariable Long sucursalId,
            @Valid @RequestBody ProductoRequest request) {
        return agregarProductoUseCase.crear(request, sucursalId)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }

}
