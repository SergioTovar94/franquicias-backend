package com.sergio.franquicias.domain.exception;

public class RecursoDuplicadoException extends RuntimeException {
    public RecursoDuplicadoException(String tipo, String nombre) {
        super("Ya existe " + tipo + " con el nombre '" + nombre + "'");
    }
}
