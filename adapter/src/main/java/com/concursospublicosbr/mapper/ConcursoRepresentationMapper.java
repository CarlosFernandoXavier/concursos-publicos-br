package com.concursospublicosbr.mapper;

import com.concursospublicosbr.model.ConcursoRepresentation;
import com.concursospublicosbr.domain.model.Concurso;
import org.mapstruct.Mapper;

@Mapper
public interface ConcursoRepresentationMapper {
    ConcursoRepresentation toConcursoRepresentation(Concurso concurso);
}
