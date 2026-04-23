package com.sergio.franquicias.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sergio.franquicias.domain.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {
    Flux<Producto> findBySucursalId(Long sucursalId);

    Mono<Void> deleteBySucursalId(Long sucursalId);

    Mono<Producto> findTopBySucursalIdOrderByStockDesc(Long sucursalId);
}
