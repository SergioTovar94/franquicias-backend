package com.sergio.franquicias.domain.repository;

import com.sergio.franquicias.domain.model.Sucursal;

import reactor.core.publisher.Mono;

public interface SucursalRepositoryPort {
    Mono<Sucursal> save(Sucursal sucursal, Long franquiciaId);

    Mono<Sucursal> findById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<Boolean> existsById(Long id);

    Mono<Sucursal> updateNombre(Long id, String nuevoNombre);
}
