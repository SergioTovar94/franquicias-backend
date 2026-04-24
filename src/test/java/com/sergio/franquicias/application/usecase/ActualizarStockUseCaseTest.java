package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.ProductoResponse;
import com.sergio.franquicias.application.dto.UpdateStockRequest;
import com.sergio.franquicias.application.mapper.ProductoResponseMapper;
import com.sergio.franquicias.domain.model.Producto;
import com.sergio.franquicias.domain.service.ProductoService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ActualizarStockUseCaseTest {
    @Mock
    private ProductoService productoService;

    @Mock
    private ProductoResponseMapper responseMapper;

    @InjectMocks
    private ActualizarStockUseCase useCase;

    @Test
    void actualizar_deberiaRetornarProductoResponse() {

        Long productoId = 1L;

        UpdateStockRequest request = new UpdateStockRequest();
        request.setStock(50);

        Producto producto = new Producto();
        producto.setId(productoId);
        producto.setStock(50);

        ProductoResponse response = new ProductoResponse();
        response.setId(productoId);
        response.setStock(50);

        when(productoService.actualizarStock(productoId, 50))
                .thenReturn(Mono.just(producto));

        when(responseMapper.toResponse(producto))
                .thenReturn(response);

        StepVerifier.create(useCase.actualizar(productoId, request))
                .expectNext(response)
                .verifyComplete();
    }
}
