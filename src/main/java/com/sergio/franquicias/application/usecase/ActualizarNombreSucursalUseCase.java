package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.SucursalResponse;
import com.sergio.franquicias.application.dto.UpdateNombreSucursalRequest;
import com.sergio.franquicias.application.mapper.SucursalResponseMapper;
import com.sergio.franquicias.domain.service.SucursalService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ActualizarNombreSucursalUseCase {

    private final SucursalService sucursalService;
    private final SucursalResponseMapper responseMapper;

    public Mono<SucursalResponse> actualizar(Long sucursalId, UpdateNombreSucursalRequest request) {
        return sucursalService.actualizarNombre(sucursalId, request.getNombre())
                .map(responseMapper::toResponse);
    }

}
