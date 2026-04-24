package com.sergio.franquicias.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.franquicias.application.dto.FranquiciaRequest;
import com.sergio.franquicias.application.dto.FranquiciaResponse;
import com.sergio.franquicias.application.dto.UpdateNombreFranquiciaRequest;
import com.sergio.franquicias.application.usecase.ActualizarNombreFranquiciaUseCase;
import com.sergio.franquicias.application.usecase.CrearFranquiciaUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final CrearFranquiciaUseCase crearFranquiciaUseCase;
    private final ActualizarNombreFranquiciaUseCase actualizarNombreFranquiciaUseCase;

    @PostMapping
    public Mono<ResponseEntity<FranquiciaResponse>> crear(@Valid @RequestBody FranquiciaRequest request) {
        return crearFranquiciaUseCase.crear(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @PatchMapping("/{franquiciaId}/nombre")
    public Mono<ResponseEntity<FranquiciaResponse>> actualizarNombre(
            @PathVariable Long franquiciaId,
            @Valid @RequestBody UpdateNombreFranquiciaRequest request) {
        return actualizarNombreFranquiciaUseCase.actualizar(franquiciaId, request)
                .map(ResponseEntity::ok);
    }

}
