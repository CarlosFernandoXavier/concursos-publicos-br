package com.concursospublicosbr.mapper;

import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.domain.response.ConcursoPublicResponse;
import org.mapstruct.Mapper;

@Mapper(uses = {ConcursoMapper.class})
public interface ConcursoPublicoMapper {
    ConcursoPublico toConcursoPublico(ConcursoPublicResponse concursoPublicResponse);
}
