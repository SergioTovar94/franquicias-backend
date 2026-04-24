package com.sergio.franquicias.domain.service;

import org.springframework.stereotype.Component;

import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.model.ProductoSucursal;
import com.sergio.franquicias.domain.repository.ProductoRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
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

    public Flux<ProductoSucursal> obtenerProductosMasStockPorFranquicia(Long franquiciaId) {
        return sucursalRepositoryPort.findByFranquiciaId(franquiciaId)
                .flatMap(sucursal -> productoRepositoryPort.findBySucursalId(sucursal.getId())
                        .reduce((p1, p2) -> p1.getStock() > p2.getStock() ? p1 : p2)
                        .map(productoTop -> new ProductoSucursal(
                                sucursal.getNombre(),
                                productoTop.getNombre(),
                                productoTop.getStock())));
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
        return sucursalRepositoryPort.existsById(sucursalId)
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new RecursoNoEncontradoException("Sucursal no encontrada")))
                .flatMap(ok -> productoRepositoryPort.existsById(productoId)
                        .filter(Boolean::booleanValue)
                        .switchIfEmpty(Mono.error(new RecursoNoEncontradoException("Producto no encontrado"))))
                .flatMap(ok -> productoRepositoryPort.deleteById(productoId));
    }

}
