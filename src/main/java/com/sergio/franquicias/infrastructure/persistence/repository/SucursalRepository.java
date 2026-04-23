package com.sergio.franquicias.infrastructure.persistence.repository;

import com.sergio.franquicias.infrastructure.persistence.entity.SucursalEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SucursalRepository extends ReactiveCrudRepository<SucursalEntity, Long> {
    Flux<SucursalEntity> findByFranquiciaId(Long franquiciaId);

    Mono<Void> deleteByFranquiciaId(Long franquiciaId);
}
