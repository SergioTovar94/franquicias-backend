package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.dto.UpdateNombreProductoRequest;
import com.sergio.franquicias.application.mapper.ProductoResponseMapper;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.service.ProductoService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)

public class ActualizarNombreProductoUseCaseTest {
    @Mock
    private ProductoService productoService;

    @Mock
    private ProductoResponseMapper responseMapper;

    @InjectMocks
    private ActualizarNombreProductoUseCase useCase;

    @Test
    void actualizar_deberiaRetornarProductoResponse() {

        Long productoId = 1L;

        UpdateNombreProductoRequest request = new UpdateNombreProductoRequest();
        request.setNombre("Nuevo nombre producto");

        Producto producto = new Producto();
        producto.setId(productoId);
        producto.setNombre("Nuevo nombre producto");

        ProductoResponse response = new ProductoResponse();
        response.setId(productoId);
        response.setNombre("Nuevo nombre producto");

        when(productoService.actualizarNombre(productoId, "Nuevo nombre producto"))
                .thenReturn(Mono.just(producto));

        when(responseMapper.toResponse(producto))
                .thenReturn(response);

        StepVerifier.create(useCase.actualizar(productoId, request))
                .expectNext(response)
                .verifyComplete();
    }
}
