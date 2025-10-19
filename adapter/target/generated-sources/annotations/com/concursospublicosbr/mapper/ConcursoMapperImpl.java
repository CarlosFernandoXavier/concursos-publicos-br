package com.concursospublicosbr.mapper;

import com.concursospublicosbr.domain.model.Concurso;
import com.concursospublicosbr.domain.response.ConcursoResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-19T11:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.19 (Ubuntu)"
)
public class ConcursoMapperImpl implements ConcursoMapper {

    @Override
    public Concurso toConcurso(ConcursoResponse concursoResponse) {
        if ( concursoResponse == null ) {
            return null;
        }

        Concurso.ConcursoBuilder concurso = Concurso.builder();

        concurso.orgao( concursoResponse.getOrgao() );
        concurso.vagas( concursoResponse.getVagas() );

        return concurso.build();
    }
}
