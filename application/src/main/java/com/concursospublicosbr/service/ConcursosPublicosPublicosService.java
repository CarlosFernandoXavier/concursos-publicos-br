package com.concursospublicosbr.service;

import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.enums.UnidadeFederativaEnum;
import com.concursospublicosbr.exception.BusinessException;
import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import com.concursospublicosbr.port.out.ConcursosPublicosRepositoryPort;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

@RequiredArgsConstructor
public class ConcursosPublicosPublicosService implements ConcursosPublicosServicePort {

    private static final Logger log = LoggerFactory.getLogger(ConcursosPublicosPublicosService.class);
    private final ConcursosPublicosRepositoryPort concursosPublicosRepositoryPort;


    @Override
    public ConcursoPublico getConcursosPublicosDisponiveis(String uf) {
        try {
            String ufFormatada = uf.toLowerCase(Locale.ROOT);
            if (!UnidadeFederativaEnum.ehUnidadeFederativaValida(ufFormatada)) {
                throw new BusinessException("Unidade federativa inexistente. UFs válidas: ac, al, ap, am, ba, ce, df, es, go, ma, mt, ms, mg, pa, pb, pr, pe, pi, rj, rn, rs, ro, rr, sc, sp, se, to");
            }
            return concursosPublicosRepositoryPort.getConcursosPublicos(ufFormatada);
        } catch (Exception e) {
            String mensagem = String.format("Erro ao buscar os concursos públicos disponívies - mensagem capturada: %s", e.getMessage());
            log.error(mensagem);
            throw e;
        }
    }
}
