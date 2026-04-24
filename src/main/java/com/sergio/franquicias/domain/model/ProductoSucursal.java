package com.sergio.franquicias.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSucursal {
    private String nombreSucursal;
    private String nombreProducto;
    private Integer stock;
}
