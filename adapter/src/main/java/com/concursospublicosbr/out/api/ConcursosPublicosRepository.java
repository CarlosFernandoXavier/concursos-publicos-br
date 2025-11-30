package com.concursospublicosbr.out.api;

import com.concursospublicosbr.mapper.ConcursoPublicoMapper;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.domain.response.ConcursoPublicResponse;
import com.concursospublicosbr.port.out.ConcursosPublicosRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ConcursosPublicosRepository implements ConcursosPublicosRepositoryPort {

    @Value("${concursos.api.base-url}")
    private String concursosApiBaseUrl;

    private final RestTemplate restTemplate;
    private final ConcursoPublicoMapper concursoPublicoMapper;

    public ConcursosPublicosRepository(RestTemplate restTemplate, ConcursoPublicoMapper concursoPublicoMapper) {
        this.restTemplate = restTemplate;
        this.concursoPublicoMapper = concursoPublicoMapper;
    }

    @Override
    public ConcursoPublico getConcursosPublicos(String uf) {
        String base = concursosApiBaseUrl.endsWith("/") ? concursosApiBaseUrl : concursosApiBaseUrl + "/";
        String targetUrl = base + uf;
        ResponseEntity<ConcursoPublicResponse> response = restTemplate.getForEntity(targetUrl, ConcursoPublicResponse.class);
        return concursoPublicoMapper.toConcursoPublico(response.getBody());
    }
}
