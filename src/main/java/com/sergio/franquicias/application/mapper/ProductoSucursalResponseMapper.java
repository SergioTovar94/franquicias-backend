package com.sergio.franquicias.application.mapper;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.application.dto.ProductoSucursalResponse;
import com.sergio.franquicias.domain.model.ProductoSucursal;

@Component
public class ProductoSucursalResponseMapper {

    public ProductoSucursalResponse toResponse(ProductoSucursal productoSucursal) {
        ProductoSucursalResponse response = new ProductoSucursalResponse();
        response.setNombreSucursal(productoSucursal.getNombreSucursal());
        response.setNombreProducto(productoSucursal.getNombreProducto());
        response.setStock(productoSucursal.getStock());
        return response;
    }

}
