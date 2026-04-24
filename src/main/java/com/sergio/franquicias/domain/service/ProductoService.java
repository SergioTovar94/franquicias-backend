package com.sergio.franquicias.domain.service;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.repository.ProductoRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final SucursalRepositoryPort sucursalRepositoryPort;

    public Mono<Producto> validarYGuardar(Producto producto, Long sucursalId) {
        return sucursalRepositoryPort.existsById(sucursalId)
                .flatMap(existe -> {
                    if (!existe) {
                        return Mono.error(new RecursoNoEncontradoException("Sucursal no encontrada"));
                    }
                    return productoRepositoryPort.save(producto, sucursalId);
                });
    }

    public Mono<Producto> actualizarStock(Long productoId, Integer nuevoStock) {
        return productoRepositoryPort.updateStock(productoId, nuevoStock)
                .switchIfEmpty(Mono.error(new RecursoNoEncontradoException("Producto no encontrado")));
    }

    public Mono<Producto> actualizarNombre(Long id, String nuevoNombre) {
        return productoRepositoryPort.updateNombre(id, nuevoNombre)
                .switchIfEmpty(Mono.error(new RecursoNoEncontradoException("Producto no encontrado")));
    }

    public Mono<Void> eliminar(Long productoId, Long sucursalId) {
        return validarSucursal(sucursalId)
                .then(validarProducto(productoId))
                .then(productoRepositoryPort.deleteById(productoId));
    }

    private Mono<Void> validarSucursal(Long sucursalId) {
        return sucursalRepositoryPort.existsById(sucursalId)
                .flatMap(existe -> existe
                        ? Mono.empty()
                        : Mono.error(new RecursoNoEncontradoException("Sucursal no encontrada")));
    }

    private Mono<Void> validarProducto(Long productoId) {
        return sucursalRepositoryPort.existsById(productoId)
                .flatMap(existe -> existe
                        ? Mono.empty()
                        : Mono.error(new RecursoNoEncontradoException("Producto no encontrado")));
    }

}
