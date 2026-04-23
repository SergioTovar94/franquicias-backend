package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.SucursalRequest;
import com.sergio.franquicias.domain.model.Sucursal;

@Component
public class SucursalRequestMapper {

    public Sucursal toDomain(SucursalRequest request) {
        Sucursal domain = new Sucursal();
        domain.setNombre(request.getNombre());
        return domain;
    }

}
