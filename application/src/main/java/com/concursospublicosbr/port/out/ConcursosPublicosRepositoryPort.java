package com.concursospublicosbr.port.out;

import com.concursospublicosbr.domain.model.ConcursoPublico;

public interface ConcursosPublicosRepositoryPort {

    ConcursoPublico getConcursosPublicos(String uf);
}
