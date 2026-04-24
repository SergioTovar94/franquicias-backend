package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.SucursalRequest;
import com.sergio.franquicias.application.dto.SucursalResponse;
import com.sergio.franquicias.application.mapper.SucursalRequestMapper;
import com.sergio.franquicias.application.mapper.SucursalResponseMapper;
import com.sergio.franquicias.domain.model.Sucursal;
import com.sergio.franquicias.domain.service.SucursalService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class AgregarSucursalUseCaseTest {

    @Mock
    private SucursalService sucursalService;

    @Mock
    private SucursalRequestMapper requestMapper;

    @Mock
    private SucursalResponseMapper responseMapper;

    @InjectMocks
    private AgregarSucursalUseCase useCase;

    @Test
    void crear_deberiaGuardarSucursalYRetornarResponse() {

        Long franquiciaId = 1L;

        SucursalRequest request = new SucursalRequest();

        Sucursal sucursal = new Sucursal();
        Sucursal sucursalGuardada = new Sucursal();

        SucursalResponse response = new SucursalResponse();

        when(requestMapper.toDomain(request))
                .thenReturn(sucursal);

        when(sucursalService.validarYGuardar(sucursal, franquiciaId))
                .thenReturn(Mono.just(sucursalGuardada));

        when(responseMapper.toResponse(sucursalGuardada))
                .thenReturn(response);

        StepVerifier.create(useCase.crear(request, franquiciaId))
                .expectNext(response)
                .verifyComplete();
    }
}