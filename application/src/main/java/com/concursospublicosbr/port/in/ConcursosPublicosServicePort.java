package com.concursospublicosbr.port.in;

import com.concursospublicosbr.domain.model.ConcursoPublico;

public interface ConcursosPublicosServicePort {
    ConcursoPublico getConcursosPublicosDisponiveis(String uf);
}
