package com.sergio.franquicias.infrastructure.persistence.repository;

import com.sergio.franquicias.infrastructure.persistence.entity.ProductoEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<ProductoEntity, Long> {
    Flux<ProductoEntity> findBySucursalId(Long sucursalId);

    Mono<Void> deleteBySucursalId(Long sucursalId);

    Mono<ProductoEntity> findTopBySucursalIdOrderByStockDesc(Long sucursalId);
}
