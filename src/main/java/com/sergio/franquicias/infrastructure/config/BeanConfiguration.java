package com.sergio.franquicias.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sergio.franquicias.domain.repository.FranquiciaRepositoryPort;
import com.sergio.franquicias.domain.repository.ProductoRepositoryPort;
import com.sergio.franquicias.domain.repository.SucursalRepositoryPort;
import com.sergio.franquicias.domain.service.FranquiciaService;
import com.sergio.franquicias.domain.service.ProductoService;
import com.sergio.franquicias.domain.service.SucursalService;

@Configuration
public class BeanConfiguration {

    @Bean
    public FranquiciaService franquiciaService(
            FranquiciaRepositoryPort franquiciaRepositoryPort) {
        return new FranquiciaService(franquiciaRepositoryPort);
    }

    @Bean
    public ProductoService productoService(
            ProductoRepositoryPort productoRepositoryPort,
            SucursalRepositoryPort sucursalRepositoryPort) {
        return new ProductoService(productoRepositoryPort, sucursalRepositoryPort);
    }

    @Bean
    public SucursalService SucursalService(
            SucursalRepositoryPort sucursalRepositoryPort,
            FranquiciaRepositoryPort franquiciaRepositoryPort) {
        return new SucursalService(sucursalRepositoryPort, franquiciaRepositoryPort);
    }

}
