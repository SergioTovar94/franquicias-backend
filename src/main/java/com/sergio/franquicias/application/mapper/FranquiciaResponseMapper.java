package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.FranquiciaResponse;
import com.sergio.franquicias.domain.model.Franquicia;

@Component
public class FranquiciaResponseMapper {
    public FranquiciaResponse toResponse(Franquicia domain) {
        FranquiciaResponse response = new FranquiciaResponse();
        response.setId(domain.getId());
        response.setNombre(domain.getNombre());
        return response;
    }
}
