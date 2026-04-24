package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.infrastructure.persistence.entity.ProductoEntity;

@Component
public class ProductoMapper {
    public Producto toDomain(ProductoEntity entity) {
        Producto domain = new Producto();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setStock(entity.getStock());
        return domain;
    }

    public ProductoEntity toEntity(Producto domain, Long sucursalId) {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(domain.getId());
        entity.setNombre((domain.getNombre()));
        entity.setStock(domain.getStock());
        entity.setSucursalId(sucursalId);
        return entity;
    }
}
