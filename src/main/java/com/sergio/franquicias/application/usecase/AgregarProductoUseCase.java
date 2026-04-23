package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.ProductoRequest;
import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.mapper.ProductoRequestMapper;
import com.sergio.franquicias.application.mapper.ProductoResponseMapper;
import com.sergio.franquicias.domain.service.ProductoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AgregarProductoUseCase {

    private final ProductoService productoService;
    private final ProductoRequestMapper requestMapper;
    private final ProductoResponseMapper responseMapper;

    public Mono<ProductoResponse> crear(ProductoRequest request, Long sucursalId) {
        return Mono.just(request)
                .map(requestMapper::toDomain)
                .flatMap(producto -> productoService.validarYGuardar(producto, sucursalId))
                .map(responseMapper::toResponse);
    }

}
