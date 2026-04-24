package com.sergio.franquicias.domain.service;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class SucursalService {

    private final SucursalRepositoryPort sucursalRepositoryPort;
    private final FranquiciaRepositoryPort franquiciaRepositoryPort;

    public Mono<Sucursal> validarYGuardar(Sucursal sucursal, Long franquiciaId) {
        return franquiciaRepositoryPort.existsById(franquiciaId)
                .flatMap(existe -> {
                    if (!existe) {
                        return Mono.error(new RecursoNoEncontradoException("Franquicia no encontrada"));
                    }
                    return sucursalRepositoryPort.save(sucursal, franquiciaId);

                });
    }

    public Mono<Sucursal> actualizarNombre(Long id, String nuevoNombre) {
        return sucursalRepositoryPort.updateNombre(id, nuevoNombre)
                .switchIfEmpty(Mono.error(new RecursoNoEncontradoException("Sucursal no encontrado")));
    }

}
