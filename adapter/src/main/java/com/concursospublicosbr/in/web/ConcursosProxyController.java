package com.concursospublicosbr.in.web;

import com.concursospublicosbr.api.model.ConcursoPublicoRepresentation;
import com.concursospublicosbr.mapper.ConcursoPublicoRepresentationMapper;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import com.concursospublicosbr.api.ConcursosApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class ConcursosProxyController implements ConcursosApi {

    private final ConcursosPublicosServicePort concursosPublicosService;
    private final ConcursoPublicoRepresentationMapper concursoPublicoRepresentationMapper;

    @Override
    @GetMapping("/api/concursos/concursos-publicos")
    public ResponseEntity<ConcursoPublicoRepresentation> proxyGet(@RequestParam("uf") String uf) {
        ConcursoPublico response = concursosPublicosService.getConcursosPublicosDisponiveis(uf);
        ConcursoPublicoRepresentation concursoPublicoRepresentation = concursoPublicoRepresentationMapper.toConcursoPublicoRepresentation(response);
        return ResponseEntity.ok(concursoPublicoRepresentation);
    }

    /*TODO quando voltar:
    *  Adicionar autenticação jwt
    *  Fazer o front deste projeto
    *  Encontrar um lugar para fazer o deploy do front e back
    *  Mostrar os resultados no Linkedin
    * */
}
