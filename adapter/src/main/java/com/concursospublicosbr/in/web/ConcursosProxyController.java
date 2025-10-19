package com.concursospublicosbr.in.web;

import com.concursospublicosbr.api.model.ConcursoPublicoRepresentation;
import com.concursospublicosbr.mapper.ConcursoPublicoRepresentationMapper;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import com.concursospublicosbr.api.ConcursosApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConcursosProxyController implements ConcursosApi {

    private final ConcursosPublicosServicePort concursosPublicosService;
    private final ConcursoPublicoRepresentationMapper concursoPublicoRepresentationMapper;

    @Override
    public ResponseEntity<ConcursoPublicoRepresentation> proxyGet(String uf) {
        ConcursoPublico response = concursosPublicosService.getConcursosPublicosDisponiveis(uf);
        ConcursoPublicoRepresentation concursoPublicoRepresentation = concursoPublicoRepresentationMapper.toConcursoPublicoRepresentation(response);
        return ResponseEntity.ok(concursoPublicoRepresentation);
    }
}
