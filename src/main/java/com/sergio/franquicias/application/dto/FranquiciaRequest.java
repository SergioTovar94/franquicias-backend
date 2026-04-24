package com.sergio.franquicias.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FranquiciaRequest {
    @NotBlank(message = "El nombre de la franquicia es obligatorio")
    private String nombre;
}
