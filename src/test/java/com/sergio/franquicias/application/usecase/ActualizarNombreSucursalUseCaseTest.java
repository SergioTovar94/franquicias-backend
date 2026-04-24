package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.SucursalResponse;
import com.sergio.franquicias.application.dto.UpdateNombreSucursalRequest;
import com.sergio.franquicias.application.mapper.SucursalResponseMapper;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.service.SucursalService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)

public class ActualizarNombreSucursalUseCaseTest {
    @Mock
    private SucursalService sucursalService;

    @Mock
    private SucursalResponseMapper responseMapper;

    @InjectMocks
    private ActualizarNombreSucursalUseCase useCase;

    @Test
    void actualizar_deberiaRetornarSucursalResponse() {

        Long sucursalId = 1L;

        UpdateNombreSucursalRequest request = new UpdateNombreSucursalRequest();
        request.setNombre("Sucursal Centro");

        Sucursal sucursal = new Sucursal();
        sucursal.setId(sucursalId);
        sucursal.setNombre("Sucursal Centro");

        SucursalResponse response = new SucursalResponse();
        response.setId(sucursalId);
        response.setNombre("Sucursal Centro");

        when(sucursalService.actualizarNombre(sucursalId, "Sucursal Centro"))
                .thenReturn(Mono.just(sucursal));

        when(responseMapper.toResponse(sucursal))
                .thenReturn(response);

        StepVerifier.create(useCase.actualizar(sucursalId, request))
                .expectNext(response)
                .verifyComplete();
    }
}