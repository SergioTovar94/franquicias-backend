package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.FranquiciaRequest;
import com.sergio.franquicias.application.dto.FranquiciaResponse;
import com.sergio.franquicias.application.mapper.FranquiciaRequestMapper;
import com.sergio.franquicias.application.mapper.FranquiciaResponseMapper;
import com.sergio.franquicias.domain.service.FranquiciaService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CrearFranquiciaUseCase {

    private final FranquiciaService franquiciaService;
    private final FranquiciaRequestMapper requestMapper;
    private final FranquiciaResponseMapper responseMapper;

    public Mono<FranquiciaResponse> crear(FranquiciaRequest request) {
        return Mono.just(request)
                .map(requestMapper::toDomain)
                .flatMap(franquiciaService::validarYGuardar)
                .map(responseMapper::toResponse);
    }

}
