package com.sergio.franquicias.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoResponse {
    private Long id;
    private String nombre;
    private Integer stock;
}
