package com.sergio.franquicias.infrastructure.web.controller;

import com.sergio.franquicias.application.usecase.ActualizarStockUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.franquicias.application.dto.ProductoRequest;
import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.dto.UpdateStockRequest;
import com.sergio.franquicias.application.usecase.AgregarProductoUseCase;
import com.sergio.franquicias.application.usecase.EliminarProductoUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("sucursales/{sucursalId}/productos")
@RequiredArgsConstructor
public class ProductoController {

        private final AgregarProductoUseCase agregarProductoUseCase;
        private final EliminarProductoUseCase eliminarProductoUseCase;
        private final ActualizarStockUseCase actualizarStockUseCase;

        @PostMapping
        public Mono<ResponseEntity<ProductoResponse>> agregar(
                        @PathVariable Long sucursalId,
                        @Valid @RequestBody ProductoRequest request) {
                return agregarProductoUseCase.crear(request, sucursalId)
                                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
        }

        @PatchMapping("/{productoId}/stock")
        public Mono<ResponseEntity<ProductoResponse>> actualizarStock(
                        @PathVariable Long sucursalId,
                        @PathVariable Long productoId,
                        @Valid @RequestBody UpdateStockRequest request) {
                return actualizarStockUseCase.actualizar(productoId, request)
                                .map(ResponseEntity::ok);
        }

        @DeleteMapping
        public Mono<ResponseEntity<Void>> eliminar(
                        @PathVariable Long sucursalId,
                        @PathVariable Long productoId) {
                return eliminarProductoUseCase.eliminar(productoId, sucursalId)
                                .then(Mono.just(ResponseEntity.noContent().build()));
        }

}
