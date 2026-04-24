package com.sergio.franquicias.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.domain.exception.RecursoDuplicadoException;
import com.sergio.franquicias.domain.exception.RecursoNoEncontradoException;
import com.sergio.franquicias.domain.model.Franquicia;
import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class FranquiciaServiceTest {

        @Mock
        private FranquiciaRepositoryPort franquiciaRepositoryPort;

        @InjectMocks
        private FranquiciaService franquiciaService;

        @Test
        void guardarFranquicia_CuandoNombreNoExiste_DeberiaGuardar() {

                Franquicia franquicia = new Franquicia();
                franquicia.setNombre("Burguer King");

                Franquicia franquiciaGuardada = new Franquicia();
                franquiciaGuardada.setId(1L);
                franquiciaGuardada.setNombre("Burguer King");

                when(franquiciaRepositoryPort.findByNombre("Burguer King"))
                                .thenReturn(Mono.empty());
                when(franquiciaRepositoryPort.save(any(Franquicia.class)))
                                .thenReturn(Mono.just(franquiciaGuardada));

                StepVerifier.create(franquiciaService.validarYGuardar(franquicia))
                                .expectNext(franquiciaGuardada)
                                .verifyComplete();
        }

        @Test
        void guardarFranquicia_CuandoNombreExiste_DeberiaLanzarError() {
                Franquicia franquicia = new Franquicia();
                franquicia.setNombre("Burguer King");

                Franquicia existente = new Franquicia();
                existente.setId(1L);
                existente.setNombre("Burguer King");

                when(franquiciaRepositoryPort.findByNombre("Burguer King"))
                                .thenReturn(Mono.just(existente));

                StepVerifier.create(franquiciaService.validarYGuardar(franquicia))
                                .expectError(RecursoDuplicadoException.class)
                                .verify();
        }

        @Test
        void actualizarNombre_CuandoFranquiciaExiste_DeberiaActualizar() {
                Long id = 1L;
                String nuevoNombre = "Nuevo Nombre";

                Franquicia actualizada = new Franquicia();
                actualizada.setId(id);
                actualizada.setNombre(nuevoNombre);

                when(franquiciaRepositoryPort.updateNombre(id, nuevoNombre))
                                .thenReturn(Mono.just(actualizada));

                StepVerifier.create(
                                franquiciaService.actualizarNombre(id, nuevoNombre))
                                .expectNext(actualizada)
                                .verifyComplete();
        }

        @Test
        void actualizarNombre_CuandoFranquiciaNoExiste_DeberiaLanzarError() {
                Long id = 1L;
                String nuevoNombre = "Nuevo Nombre";

                when(franquiciaRepositoryPort.updateNombre(id, nuevoNombre))
                                .thenReturn(Mono.empty());

                StepVerifier.create(
                                franquiciaService.actualizarNombre(id, nuevoNombre))
                                .expectError(RecursoNoEncontradoException.class)
                                .verify();

        }

}
