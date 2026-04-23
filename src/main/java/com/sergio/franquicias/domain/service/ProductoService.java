package com.sergio.franquicias.domain.service;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.repository.ProductoRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final SucursalRepositoryPort sucursalRepositoryPort;

    public Mono<Producto> validarYGuardar(Producto producto, Long idSucursal) {
        return sucursalRepositoryPort.existsById(idSucursal)
                .flatMap(existe -> {
                    if (!existe) {
                        return Mono.error(new RuntimeException("Sucursal no encontrada"));
                    }
                    return productoRepositoryPort.save(producto, idSucursal);
                });
    }

}
