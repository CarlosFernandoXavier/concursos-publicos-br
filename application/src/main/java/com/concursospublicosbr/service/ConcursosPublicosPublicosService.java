package com.concursospublicosbr.service;

import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import com.concursospublicosbr.port.out.ConcursosPublicosRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcursosPublicosPublicosService implements ConcursosPublicosServicePort {

    private final ConcursosPublicosRepositoryPort concursosPublicosRepositoryPort;


    @Override
    public ConcursoPublico getConcursosPublicosDisponiveis(String uf) {
        try {
            return concursosPublicosRepositoryPort.getConcursosPublicos(uf);
        } catch (Exception e) {
           Integer a = 10;
        }
        return null;
    }
}
