package com.sergio.franquicias.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sergio.franquicias.domain.model.Sucursal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SucursalRepository extends ReactiveCrudRepository<Sucursal, Long> {
    Flux<Sucursal> findByFranquiciaId(Long franquiciaId);

    Mono<Void> deleteByFranquiciaId(Long franquiciaId);
}
