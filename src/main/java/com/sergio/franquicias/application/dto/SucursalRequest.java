package com.sergio.franquicias.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SucursalRequest {
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String nombre;

}
