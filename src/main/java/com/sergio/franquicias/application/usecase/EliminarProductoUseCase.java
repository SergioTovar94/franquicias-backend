package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.service.ProductoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EliminarProductoUseCase {

    private final ProductoService productoService;

    public Mono<Void> eliminar(Long productoId, Long sucursalId) {
        return productoService.eliminar(productoId, sucursalId);
    }

}
