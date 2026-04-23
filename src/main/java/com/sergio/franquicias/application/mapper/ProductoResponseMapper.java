package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.domain.model.Producto;

@Component
public class ProductoResponseMapper {

    public ProductoResponse toResponse(Producto domain) {
        ProductoResponse response = new ProductoResponse();
        response.setId(domain.getId());
        response.setNombre(domain.getNombre());
        response.setStock(domain.getStock());
        return response;
    }

}
