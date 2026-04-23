package com.sergio.franquicias.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoSucursalResponse {
    private String nombreSucursal;
    private String nombreProducto;
    private Integer stock;
}
