package com.sergio.franquicias.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.mapper.SucursalMapper;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;
import com.sergio.franquicias.infrastructure.persistence.entity.SucursalEntity;
import com.sergio.franquicias.infrastructure.persistence.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SucursalRepositoryAdapter implements SucursalRepositoryPort {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper mapper;

    @Override
    public Mono<Sucursal> save(Sucursal sucursal, Long franquiciaId) {
        SucursalEntity entity = mapper.toEntity(sucursal, franquiciaId);
        return sucursalRepository.save(entity).map(mapper::toDomain);
    }

    @Override
    public Mono<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return sucursalRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return sucursalRepository.existsById(id);
    }

    @Override
    public Mono<Sucursal> updateNombre(Long id, String nuevoNombre) {
        return sucursalRepository.findById(id)
                .flatMap(entity -> {
                    entity.setNombre(nuevoNombre);
                    return sucursalRepository.save(entity);
                })
                .map(mapper::toDomain);
    }
}
