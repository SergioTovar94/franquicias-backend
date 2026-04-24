package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.ProductoRequest;
import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.mapper.ProductoRequestMapper;
import com.sergio.franquicias.application.mapper.ProductoResponseMapper;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.service.ProductoService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)

public class AgregarProductoUseCaseTest {

        @Mock
        private ProductoService productoService;

        @Mock
        private ProductoRequestMapper requestMapper;

        @Mock
        private ProductoResponseMapper responseMapper;

        @InjectMocks
        private AgregarProductoUseCase useCase;

        @Test
        void crear_deberiaGuardarProductoYRetornarResponse() {

                Long sucursalId = 1L;

                ProductoRequest request = new ProductoRequest();

                Producto producto = new Producto();
                Producto productoGuardado = new Producto();

                ProductoResponse response = new ProductoResponse();

                when(requestMapper.toDomain(request))
                                .thenReturn(producto);

                when(productoService.validarYGuardar(producto, sucursalId))
                                .thenReturn(Mono.just(productoGuardado));

                when(responseMapper.toResponse(productoGuardado))
                                .thenReturn(response);

                StepVerifier.create(useCase.crear(request, sucursalId))
                                .expectNext(response)
                                .verifyComplete();
        }
}
