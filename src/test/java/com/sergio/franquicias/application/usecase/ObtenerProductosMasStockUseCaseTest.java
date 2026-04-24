package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.ProductoSucursalResponse;
import com.sergio.franquicias.application.mapper.ProductoSucursalResponseMapper;
import com.sergio.franquicias.domain.model.ProductoSucursal;
import com.sergio.franquicias.domain.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ObtenerProductosMasStockUseCaseTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private ProductoSucursalResponseMapper responseMapper;

    @InjectMocks
    private ObtenerProductosMasStockUseCase useCase;

    @Test
    void obtener_deberiaRetornarProductosMapeados() {

        Long franquiciaId = 1L;

        ProductoSucursal domain = new ProductoSucursal("Sucursal 1", "Producto 1", 10);
        ProductoSucursalResponse response = new ProductoSucursalResponse();

        when(productoService.obtenerProductosMasStockPorFranquicia(franquiciaId))
                .thenReturn(Flux.just(domain));

        when(responseMapper.toResponse(domain))
                .thenReturn(response);

        StepVerifier.create(useCase.obtener(franquiciaId))
                .expectNext(response)
                .verifyComplete();

        verify(productoService).obtenerProductosMasStockPorFranquicia(franquiciaId);
    }
}