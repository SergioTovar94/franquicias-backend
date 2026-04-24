package com.sergio.franquicias.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateNombreFranquiciaRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
