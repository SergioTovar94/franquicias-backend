package com.sergio.franquicias.application.usecase;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.ProductoSucursalResponse;
import com.sergio.franquicias.application.mapper.ProductoSucursalResponseMapper;
import com.sergio.franquicias.domain.service.ProductoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ObtenerProductosMasStockUseCase {

    private final ProductoService productoService;
    private final ProductoSucursalResponseMapper responseMapper;

    public Flux<ProductoSucursalResponse> obtener(Long franquiciaId) {
        return productoService.obtenerProductosMasStockPorFranquicia(franquiciaId)
                .map(responseMapper::toResponse);
    }
}
