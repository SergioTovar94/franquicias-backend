package com.sergio.franquicias.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("sucursales")
public class SucursalEntity {

    @Id
    private Long id;
    private String nombre;

    @Column("franquicia_id")
    private Long franquiciaId;
}
