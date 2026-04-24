package com.sergio.franquicias.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.mapper.ProductoMapper;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.repository.ProductoRepositoryPort;
import com.sergio.franquicias.infrastructure.persistence.entity.ProductoEntity;
import com.sergio.franquicias.infrastructure.persistence.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoRepository productoRepository;
    private final ProductoMapper mapper;

    @Override
    public Mono<Producto> save(Producto producto, Long sucursalId) {
        ProductoEntity entity = mapper.toEntity(producto, sucursalId);
        return productoRepository.save(entity).map(mapper::toDomain);
    }

    @Override
    public Mono<Producto> findById(Long id) {
        return productoRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return productoRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return productoRepository.existsById(id);
    }

    @Override
    public Mono<Producto> updateStock(Long id, Integer nuevoStock) {
        return productoRepository.findById(id)
                .flatMap(entity -> {
                    entity.setStock(nuevoStock);
                    return productoRepository.save(entity);
                })
                .map(mapper::toDomain);
    }
}
