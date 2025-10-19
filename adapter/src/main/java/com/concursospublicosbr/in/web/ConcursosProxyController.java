package com.concursospublicosbr.in.web;

import com.concursospublicosbr.mapper.ConcursoPublicoRepresentationMapper;
import com.concursospublicosbr.model.ConcursoPublicoRepresentation;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/concursos")
@AllArgsConstructor
public class ConcursosProxyController {

    private final ConcursosPublicosServicePort concursosPublicosService;
    private final ConcursoPublicoRepresentationMapper concursoPublicoRepresentationMapper;

    @GetMapping("/concursos-publicos")
    public ResponseEntity<Object> proxyGet(String uf) {
        ConcursoPublico response = concursosPublicosService.getConcursosPublicosDisponiveis(uf);
        ConcursoPublicoRepresentation concursoPublicoRepresentation = concursoPublicoRepresentationMapper.toConcursoPublicoRepresentation(response);
        return ResponseEntity.ok(concursoPublicoRepresentation);
    }
}
