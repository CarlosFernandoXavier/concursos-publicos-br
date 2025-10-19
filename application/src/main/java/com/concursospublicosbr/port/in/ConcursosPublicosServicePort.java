package com.concursospublicosbr.port.in;

import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.exception.BusinessException;

public interface ConcursosPublicosServicePort {
    ConcursoPublico getConcursosPublicosDisponiveis(String uf) throws BusinessException;
}
