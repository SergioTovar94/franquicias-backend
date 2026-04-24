package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.FranquiciaRequest;
import com.sergio.franquicias.application.dto.FranquiciaResponse;
import com.sergio.franquicias.application.mapper.FranquiciaRequestMapper;
import com.sergio.franquicias.application.mapper.FranquiciaResponseMapper;
import com.sergio.franquicias.domain.model.Franquicia;
import com.sergio.franquicias.domain.service.FranquiciaService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CrearFranquiciaUseCaseTest {

    @Mock
    private FranquiciaService franquiciaService;

    @Mock
    private FranquiciaRequestMapper requestMapper;

    @Mock
    private FranquiciaResponseMapper responseMapper;

    @InjectMocks
    private CrearFranquiciaUseCase useCase;

    @Test
    void crear_deberiaCrearFranquiciaYRetornarResponse() {

        FranquiciaRequest request = new FranquiciaRequest();

        Franquicia domain = new Franquicia();
        Franquicia saved = new Franquicia();

        FranquiciaResponse response = new FranquiciaResponse();

        when(requestMapper.toDomain(request)).thenReturn(domain);

        when(franquiciaService.validarYGuardar(domain))
                .thenReturn(Mono.just(saved));

        when(responseMapper.toResponse(saved))
                .thenReturn(response);

        StepVerifier.create(useCase.crear(request))
                .expectNext(response)
                .verifyComplete();

        verify(requestMapper).toDomain(request);
        verify(franquiciaService).validarYGuardar(domain);
        verify(responseMapper).toResponse(saved);
    }
}