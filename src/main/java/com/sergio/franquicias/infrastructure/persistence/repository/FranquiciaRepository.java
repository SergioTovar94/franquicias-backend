package com.sergio.franquicias.infrastructure.persistence.repository;

import com.sergio.franquicias.infrastructure.persistence.entity.FranquiciaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<FranquiciaEntity, Long> {
    Mono<FranquiciaEntity> findByNombre(String nombre);
}