package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.SucursalRequest;
import com.sergio.franquicias.application.dto.SucursalResponse;
import com.sergio.franquicias.application.mapper.SucursalRequestMapper;
import com.sergio.franquicias.application.mapper.SucursalResponseMapper;
import com.sergio.franquicias.domain.service.SucursalService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AgregarSucursalUseCase {

    private final SucursalService sucursalService;
    private final SucursalRequestMapper requestMapper;
    private final SucursalResponseMapper responseMapper;

    public Mono<SucursalResponse> crear(SucursalRequest request, Long franquiciaId) {
        return Mono.just(request)
                .map(requestMapper::toDomain)
                .flatMap(sucursal -> sucursalService.validarYGuardar(sucursal, franquiciaId))
                .map(responseMapper::toResponse);
    }

}
