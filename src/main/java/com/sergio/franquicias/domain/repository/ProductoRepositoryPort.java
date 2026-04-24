package com.sergio.franquicias.domain.repository;

import com.sergio.franquicias.domain.model.Producto;

import reactor.core.publisher.Mono;

public interface ProductoRepositoryPort {
    Mono<Producto> save(Producto producto, Long sucursalId);

    Mono<Producto> findById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<Boolean> existsById(Long id);

    Mono<Producto> updateStock(Long id, Integer nuevoStock);

    Mono<Producto> updateNombre(Long id, String nuevoNombre);

}
