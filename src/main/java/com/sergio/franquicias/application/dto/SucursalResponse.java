package com.sergio.franquicias.application.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SucursalResponse {
    private Long id;
    private String nombre;
    private List<ProductoResponse> productos;
}
