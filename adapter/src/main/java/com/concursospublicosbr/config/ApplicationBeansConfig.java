package com.concursospublicosbr.config;

import com.concursospublicosbr.mapper.*;
import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import com.concursospublicosbr.port.out.ConcursosPublicosRepositoryPort;
import com.concursospublicosbr.service.ConcursosPublicosPublicosService;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationBeansConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ConcursosPublicosServicePort concursosPublicosServicePort(ConcursosPublicosRepositoryPort repositoryPort) {
        return new ConcursosPublicosPublicosService(repositoryPort);
    }

    @Bean
    public ConcursoPublicoMapper concursoPublicoMapper() {
        return Mappers.getMapper(ConcursoPublicoMapper.class);
    }

    @Bean
    public ConcursoMapper concursoMapper() {
        return Mappers.getMapper(ConcursoMapper.class);
    }

    @Bean
    public ConcursoPublicoRepresentationMapper concursoPublicoRepresentationMapper() {
        return Mappers.getMapper(ConcursoPublicoRepresentationMapper.class);
    }

    @Bean
    public ConcursoRepresentationMapper concursoRepresentationMapper() {
        return Mappers.getMapper(ConcursoRepresentationMapper.class);
    }

    @Bean
    public TokenMapper tokenMapper() {
        return Mappers.getMapper(TokenMapper.class);
    }
}
