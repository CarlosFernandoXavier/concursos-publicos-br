package com.concursospublicosbr.mapper;

import com.concursospublicosbr.domain.model.Concurso;
import com.concursospublicosbr.domain.response.ConcursoResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ConcursoMapper {
    Concurso toConcurso(ConcursoResponse concursoResponse);
}
