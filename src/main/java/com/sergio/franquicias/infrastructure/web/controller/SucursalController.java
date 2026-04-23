package com.sergio.franquicias.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.franquicias.application.dto.SucursalRequest;
import com.sergio.franquicias.application.dto.SucursalResponse;
import com.sergio.franquicias.application.usecase.AgregarSucursalUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/franquicias/{franquiciaId}/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final AgregarSucursalUseCase agregarSucursalUseCase;

    @PostMapping
    public Mono<ResponseEntity<SucursalResponse>> agregar(
            @PathVariable Long franquiciaId,
            @Valid @RequestBody SucursalRequest request) {
        return agregarSucursalUseCase.crear(request, franquiciaId)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

}
