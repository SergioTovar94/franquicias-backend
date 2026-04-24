package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.FranquiciaResponse;
import com.sergio.franquicias.application.dto.UpdateNombreFranquiciaRequest;
import com.sergio.franquicias.application.mapper.FranquiciaResponseMapper;
import com.sergio.franquicias.domain.service.FranquiciaService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ActualizarNombreFranquiciaUseCase {

    private final FranquiciaService franquiciaService;
    private final FranquiciaResponseMapper responseMapper;

    public Mono<FranquiciaResponse> actualizar(Long id, UpdateNombreFranquiciaRequest request) {
        return franquiciaService.actualizarNombre(id, request.getNombre())
                .map(responseMapper::toResponse);
    }

}
