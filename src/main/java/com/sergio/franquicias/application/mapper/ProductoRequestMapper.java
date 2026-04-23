package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.ProductoRequest;
import com.sergio.franquicias.domain.model.Producto;

@Component
public class ProductoRequestMapper {

    public Producto toDomain(ProductoRequest request) {
        Producto domain = new Producto();
        domain.setNombre(request.getNombre());
        domain.setStock(request.getStock());
        return domain;
    }

}
