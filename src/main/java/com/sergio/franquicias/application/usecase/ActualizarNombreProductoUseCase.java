package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.dto.UpdateNombreProductoRequest;
import com.sergio.franquicias.application.mapper.ProductoResponseMapper;
import com.sergio.franquicias.domain.service.ProductoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ActualizarNombreProductoUseCase {

    private final ProductoService productoService;
    private final ProductoResponseMapper responseMapper;

    public Mono<ProductoResponse> actualizar(Long productoId, UpdateNombreProductoRequest request) {
        return productoService.actualizarNombre(productoId, request.getNombre())
                .map(responseMapper::toResponse);
    }

}
