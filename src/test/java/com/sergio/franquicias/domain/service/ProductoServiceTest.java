package com.sergio.franquicias.domain.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.repository.ProductoRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

        @Mock
        private ProductoRepositoryPort productoRepositoryPort;

        @Mock
        private SucursalRepositoryPort sucursalRepositoryPort;

        @InjectMocks
        private ProductoService productoService;

        @Test
        void validarYGuardar_CuandoSucursalExiste_DeberiaGuardarProducto() {
                Long sucursalId = 1L;

                Producto producto = new Producto();
                producto.setNombre("Kings Collection");

                Producto guardado = new Producto();
                guardado.setId(1L);
                guardado.setNombre("Kings Collection");

                when(sucursalRepositoryPort.existsById(sucursalId))
                                .thenReturn(Mono.just(true));
                when(productoRepositoryPort.save(producto, sucursalId))
                                .thenReturn(Mono.just(guardado));

                StepVerifier.create(
                                productoService.validarYGuardar(producto, sucursalId))
                                .expectNext(guardado)
                                .verifyComplete();
        }

        @Test
        void validarYGuardar_CuandoSucursalNoExiste_DeberiaLanzarError() {
                Long sucursalId = 1L;

                Producto producto = new Producto();
                producto.setNombre("Burguer King - Bosa");

                when(sucursalRepositoryPort.existsById(sucursalId))
                                .thenReturn(Mono.just(false));

                StepVerifier.create(
                                productoService.validarYGuardar(producto, sucursalId))
                                .expectError(RecursoNoEncontradoException.class)
                                .verify();
        }

        @Test
        void obtenerProductosMasStockPorFranquicia_CuandoExistenSucursales_DeberiaRetornarProductoConMasStock() {
                Long franquiciaId = 1L;

                Sucursal sucursal = new Sucursal();
                sucursal.setId(1L);
                sucursal.setNombre("Burguer King - Soacha");

                Producto p1 = new Producto();
                p1.setNombre("Burguer Collection");
                p1.setStock(10);

                Producto p2 = new Producto();
                p2.setNombre("Barquilla");
                p2.setStock(20);

                when(sucursalRepositoryPort.findByFranquiciaId(franquiciaId))
                                .thenReturn(Flux.just(sucursal));

                when(productoRepositoryPort.findBySucursalId(1L))
                                .thenReturn(Flux.just(p1, p2));

                StepVerifier.create(
                                productoService.obtenerProductosMasStockPorFranquicia(franquiciaId))
                                .expectNextMatches(ps -> ps.getNombreSucursal().equals("Burguer King - Soacha") &&
                                                ps.getNombreProducto().equals("Barquilla") &&
                                                ps.getStock() == 20)
                                .verifyComplete();
        }

        @Test
        void obtenerProductosMasStockPorFranquicia_CuandoNoExistenSucursales_DeberiaRetornarVacio() {
                Long franquiciaId = 1L;

                when(sucursalRepositoryPort.findByFranquiciaId(franquiciaId))
                                .thenReturn(Flux.empty());

                StepVerifier.create(
                                productoService.obtenerProductosMasStockPorFranquicia(franquiciaId))
                                .verifyComplete();
        }

        @Test
        void obtenerProductosMasStockPorFranquicia_CuandoSucursalesSinProductos_DeberiaRetornarVacio() {
                Long franquiciaId = 1L;

                Sucursal sucursal = new Sucursal();
                sucursal.setId(1L);
                sucursal.setNombre("Burguer King - Soacha");

                when(sucursalRepositoryPort.findByFranquiciaId(franquiciaId))
                                .thenReturn(Flux.just(sucursal));

                when(productoRepositoryPort.findBySucursalId(1L))
                                .thenReturn(Flux.empty());

                StepVerifier.create(
                                productoService.obtenerProductosMasStockPorFranquicia(franquiciaId))
                                .verifyComplete();
        }

        @Test
        void actualizarStock_CuandoProductoExiste_DeberiaActualizarStock() {
                Long productoId = 1L;

                int nuevoStock = 50;

                Producto actualizado = new Producto();
                actualizado.setId(productoId);
                actualizado.setStock(nuevoStock);

                when(productoRepositoryPort.updateStock(productoId, nuevoStock))
                                .thenReturn(Mono.just(actualizado));

                StepVerifier.create(
                                productoService.actualizarStock(productoId, nuevoStock))
                                .expectNext(actualizado)
                                .verifyComplete();
        }

        @Test
        void actualizarStock_CuandoProductoNoExiste_DeberiaLanzarError() {
                Long productoId = 1L;
                Integer nuevoStock = 50;

                when(productoRepositoryPort.updateStock(productoId, nuevoStock))
                                .thenReturn(Mono.empty());

                StepVerifier.create(
                                productoService.actualizarStock(productoId, nuevoStock))
                                .expectError(RecursoNoEncontradoException.class)
                                .verify();
        }

        @Test
        void actualizarNombre_CuandoProductoExiste_DeberiaActualizarNombre() {
                Long productoId = 1L;
                String nuevoNombre = "Nuevo Nombre";

                Producto actualizado = new Producto();
                actualizado.setId(productoId);
                actualizado.setNombre(nuevoNombre);

                when(productoRepositoryPort.updateNombre(productoId, nuevoNombre))
                                .thenReturn(Mono.just(actualizado));

                StepVerifier.create(
                                productoService.actualizarNombre(productoId, nuevoNombre))
                                .expectNext(actualizado)
                                .verifyComplete();
        }

        @Test
        void actualizarNombre_CuandoProductoNoExiste_DeberiaLanzarError() {
                Long id = 1L;
                String nuevoNombre = "Nuevo Nombre";

                when(productoRepositoryPort.updateNombre(id, nuevoNombre))
                                .thenReturn(Mono.empty());

                StepVerifier.create(
                                productoService.actualizarNombre(id, nuevoNombre))
                                .expectError(RecursoNoEncontradoException.class)
                                .verify();
        }

        @Test
        void eliminar_CuandoProductoYSucursalExisten_DeberiaEliminarProducto() {
                Long productoId = 1L;
                Long sucursalId = 1L;

                when(sucursalRepositoryPort.existsById(sucursalId))
                                .thenReturn(Mono.just(true));

                when(productoRepositoryPort.existsById(productoId))
                                .thenReturn(Mono.just(true));

                when(productoRepositoryPort.deleteById(productoId))
                                .thenReturn(Mono.empty());

                StepVerifier.create(
                                productoService.eliminar(productoId, sucursalId))
                                .verifyComplete();
        }

        @Test
        void eliminar_CuandoSucursalNoExiste_DeberiaLanzarError() {
                Long productoId = 1L;
                Long sucursalId = 1L;

                when(sucursalRepositoryPort.existsById(sucursalId))
                                .thenReturn(Mono.just(false));

                StepVerifier.create(
                                productoService.eliminar(productoId, sucursalId))
                                .expectError(RecursoNoEncontradoException.class)
                                .verify();
        }

        @Test
        void eliminar_CuandoProductoNoExiste_DeberiaLanzarError() {
                Long productoId = 1L;
                Long sucursalId = 1L;

                when(sucursalRepositoryPort.existsById(sucursalId))
                                .thenReturn(Mono.just(true));

                when(productoRepositoryPort.existsById(productoId))
                                .thenReturn(Mono.just(false));

                StepVerifier.create(
                                productoService.eliminar(productoId, sucursalId))
                                .expectError(RecursoNoEncontradoException.class)
                                .verify();
        }
}
