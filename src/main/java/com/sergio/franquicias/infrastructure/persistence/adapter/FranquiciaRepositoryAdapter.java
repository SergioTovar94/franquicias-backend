package com.sergio.franquicias.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.mapper.FranquiciaMapper;
import com.sergio.franquicias.domain.model.Franquicia;
import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;
import com.sergio.franquicias.infrastructure.persistence.entity.FranquiciaEntity;
import com.sergio.franquicias.infrastructure.persistence.repository.FranquiciaRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranquiciaRepositoryAdapter implements FranquiciaRepositoryPort {

    private final FranquiciaRepository franquiciaRepository;
    private final FranquiciaMapper mapper;

    @Override
    public Mono<Franquicia> save(Franquicia franquicia) {
        FranquiciaEntity entity = mapper.toEntity(franquicia);
        return franquiciaRepository.save(entity).map(mapper::toDomain);
    }

    @Override
    public Mono<Franquicia> findById(Long id) {
        return franquiciaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return franquiciaRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return franquiciaRepository.existsById(id);
    }

    @Override
    public Mono<Franquicia> findByNombre(String nombre) {
        return franquiciaRepository.findByNombre(nombre).map(mapper::toDomain);
    }

}
