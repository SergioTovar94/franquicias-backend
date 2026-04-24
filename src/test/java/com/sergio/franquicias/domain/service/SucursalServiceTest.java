package com.sergio.franquicias.domain.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {

    @Mock
    private FranquiciaRepositoryPort franquiciaRepositoryPort;

    @Mock
    private SucursalRepositoryPort sucursalRepositoryPort;

    @InjectMocks
    private SucursalService sucursalService;

    @Test
    void validarYGuardar_CuandoFranquiciaExiste_DeberiaGuardarSucursal() {
        Long franquiciaId = 1L;

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Burguer King - Bosa");

        Sucursal guardada = new Sucursal();
        guardada.setId(1L);
        guardada.setNombre("Burguer King - Bosa");

        when(franquiciaRepositoryPort.existsById(franquiciaId))
                .thenReturn(Mono.just(true));
        when(sucursalRepositoryPort.save(sucursal, franquiciaId))
                .thenReturn(Mono.just(guardada));

        StepVerifier.create(
                sucursalService.validarYGuardar(sucursal, franquiciaId))
                .expectNext(guardada)
                .verifyComplete();
    }

    @Test
    void validarYGuardar_CuandoFranquiciaNoExiste_DeberiaLanzarError() {
        Long franquiciaId = 1L;

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Burguer King - Bosa");

        when(franquiciaRepositoryPort.existsById(franquiciaId))
                .thenReturn(Mono.just(false));

        StepVerifier.create(
                sucursalService.validarYGuardar(sucursal, franquiciaId))
                .expectError(RecursoNoEncontradoException.class)
                .verify();
    }

    @Test
    void actualizarNombre_CuandoSucursalExiste_DeberiaActualizarNombre() {
        Long id = 1L;
        String nuevoNombre = "Nuevo Nombre";

        Sucursal actualizada = new Sucursal();
        actualizada.setId(id);
        actualizada.setNombre(nuevoNombre);

        when(sucursalRepositoryPort.updateNombre(id, nuevoNombre))
                .thenReturn(Mono.just(actualizada));

        StepVerifier.create(
                sucursalService.actualizarNombre(id, nuevoNombre))
                .expectNext(actualizada)
                .verifyComplete();
    }

    @Test
    void actualizarNombre_CuandoSucursalNoExiste_DeberiaLanzarError() {
        Long id = 1L;
        String nuevoNombre = "Nuevo Nombre";

        when(sucursalRepositoryPort.updateNombre(id, nuevoNombre))
                .thenReturn(Mono.empty());

        StepVerifier.create(
                sucursalService.actualizarNombre(id, nuevoNombre))
                .expectError(RecursoNoEncontradoException.class)
                .verify();
    }
}
