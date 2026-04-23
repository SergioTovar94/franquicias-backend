package com.sergio.franquicias.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Franquicia {
    private Long id;
    private String nombre;
    private List<Sucursal> sucursales;
}