package com.sergio.franquicias.domain.repository;

import com.sergio.franquicias.domain.model.Franquicia;

import reactor.core.publisher.Mono;

public interface FranquiciaRepositoryPort {
    Mono<Franquicia> save(Franquicia franquicia);

    Mono<Franquicia> findById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<Boolean> existsById(Long id);

    Mono<Franquicia> findByNombre(String nombre);

}
