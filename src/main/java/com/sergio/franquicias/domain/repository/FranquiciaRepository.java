package com.sergio.franquicias.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.sergio.franquicias.domain.model.Franquicia;

import reactor.core.publisher.Mono;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<Franquicia, String> {

    Mono<Franquicia> findByNombre(String nombre);

}
