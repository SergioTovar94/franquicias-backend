package com.sergio.franquicias.application.usecase;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sergio.franquicias.application.dto.FranquiciaResponse;
import com.sergio.franquicias.application.dto.UpdateNombreFranquiciaRequest;
import com.sergio.franquicias.application.mapper.FranquiciaResponseMapper;
import com.sergio.franquicias.domain.model.Franquicia;
import com.sergio.franquicias.domain.service.FranquiciaService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ActualizarNombreFranquiciaUseCaseTest {

    @Mock
    private FranquiciaService franquiciaService;

    @Mock
    private FranquiciaResponseMapper responseMapper;

    @InjectMocks
    private ActualizarNombreFranquiciaUseCase useCase;

    @Test
    void actualizar_deberiaRetornarFranquiciaResponse() {

        Long id = 1L;

        UpdateNombreFranquiciaRequest request = new UpdateNombreFranquiciaRequest();
        request.setNombre("Nuevo nombre");

        Franquicia franquicia = new Franquicia();
        franquicia.setId(id);
        franquicia.setNombre("Nuevo nombre");

        FranquiciaResponse response = new FranquiciaResponse();
        response.setId(id);
        response.setNombre("Nuevo nombre");

        when(franquiciaService.actualizarNombre(id, "Nuevo nombre"))
                .thenReturn(Mono.just(franquicia));

        when(responseMapper.toResponse(franquicia))
                .thenReturn(response);

        StepVerifier.create(useCase.actualizar(id, request))
                .expectNext(response)
                .verifyComplete();
    }
}
