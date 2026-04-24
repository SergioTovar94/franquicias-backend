package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.FranquiciaRequest;
import com.sergio.franquicias.domain.model.Franquicia;

@Component
public class FranquiciaRequestMapper {
    public Franquicia toDomain(FranquiciaRequest request) {
        Franquicia franquicia = new Franquicia();
        franquicia.setNombre(request.getNombre());
        return franquicia;
    }
}
