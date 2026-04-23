package com.sergio.franquicias.domain.service;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.model.Franquicia;
import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranquiciaService {

    private final FranquiciaRepositoryPort franquiciaRepositoryPort;

    public Mono<Franquicia> validarYGuardar(Franquicia franquicia) {
        return franquiciaRepositoryPort.findByNombre(franquicia.getNombre())
                .hasElement()
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new RuntimeException("Ya existe una franquicia con ese nombre"));
                    }
                    return franquiciaRepositoryPort.save(franquicia);
                });
    }

}
