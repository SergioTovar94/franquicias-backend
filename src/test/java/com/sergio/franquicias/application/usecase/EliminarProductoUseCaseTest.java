package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.domain.service.ProductoService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class EliminarProductoUseCaseTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private EliminarProductoUseCase useCase;

    @Test
    void eliminar_deberiaLlamarAlServiceYCompletar() {

        Long productoId = 1L;
        Long sucursalId = 1L;

        when(productoService.eliminar(productoId, sucursalId))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.eliminar(productoId, sucursalId))
                .verifyComplete();

        verify(productoService).eliminar(productoId, sucursalId);
    }
}