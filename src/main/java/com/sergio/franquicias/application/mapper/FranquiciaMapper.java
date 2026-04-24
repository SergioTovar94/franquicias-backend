package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.model.Franquicia;
import com.sergio.franquicias.infrastructure.persistence.entity.FranquiciaEntity;

@Component
public class FranquiciaMapper {

    public Franquicia toDomain(FranquiciaEntity entity) {
        if (entity == null)
            return null;
        Franquicia domain = new Franquicia();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        return domain;
    }

    public FranquiciaEntity toEntity(Franquicia domain) {
        if (domain == null)
            return null;
        FranquiciaEntity entity = new FranquiciaEntity();
        entity.setId(domain.getId());
        entity.setNombre((domain.getNombre()));
        return entity;
    }

}
