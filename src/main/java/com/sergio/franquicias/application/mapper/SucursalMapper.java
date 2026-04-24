package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.infrastructure.persistence.entity.SucursalEntity;

@Component
public class SucursalMapper {
    public Sucursal toDomain(SucursalEntity entity) {
        if (entity == null)
            return null;
        Sucursal domain = new Sucursal();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        return domain;
    }

    public SucursalEntity toEntity(Sucursal domain, Long franquiciaId) {
        if (domain == null)
            return null;
        SucursalEntity entity = new SucursalEntity();
        entity.setId(domain.getId());
        entity.setNombre((domain.getNombre()));
        entity.setFranquiciaId(franquiciaId);
        return entity;
    }
}
