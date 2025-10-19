package com.concursospublicosbr.mapper;

import com.concursospublicosbr.model.ConcursoPublicoRepresentation;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import org.mapstruct.Mapper;

@Mapper(uses = {ConcursoRepresentationMapper.class})
public interface ConcursoPublicoRepresentationMapper {
    ConcursoPublicoRepresentation toConcursoPublicoRepresentation(ConcursoPublico concursoPublico);
}
