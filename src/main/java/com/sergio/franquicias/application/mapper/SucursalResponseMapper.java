package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.SucursalResponse;
import com.sergio.franquicias.domain.model.Sucursal;

@Component
public class SucursalResponseMapper {

    public SucursalResponse toResponse(Sucursal domain) {
        SucursalResponse response = new SucursalResponse();
        response.setId(domain.getId());
        response.setNombre(domain.getNombre());
        return response;
    }

}
