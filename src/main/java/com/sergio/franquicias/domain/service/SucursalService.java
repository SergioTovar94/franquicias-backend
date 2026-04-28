package com.sergio.franquicias.domain.service;

import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import reactor.core.publisher.Mono;

public class SucursalService {

    private final SucursalRepositoryPort sucursalRepositoryPort;
    private final FranquiciaRepositoryPort franquiciaRepositoryPort;

    public SucursalService(
            SucursalRepositoryPort sucursalRepositoryPort,
            FranquiciaRepositoryPort franquiciaRepositoryPort) {
        this.franquiciaRepositoryPort = franquiciaRepositoryPort;
        this.sucursalRepositoryPort = sucursalRepositoryPort;
    }

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
