package com.concursospublicosbr.mapper;

import com.concursospublicosbr.domain.model.Concurso;
import com.concursospublicosbr.model.ConcursoRepresentation;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-19T11:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.19 (Ubuntu)"
)
public class ConcursoRepresentationMapperImpl implements ConcursoRepresentationMapper {

    @Override
    public ConcursoRepresentation toConcursoRepresentation(Concurso concurso) {
        if ( concurso == null ) {
            return null;
        }

        ConcursoRepresentation.ConcursoRepresentationBuilder concursoRepresentation = ConcursoRepresentation.builder();

        concursoRepresentation.orgao( concurso.getOrgao() );
        concursoRepresentation.vagas( concurso.getVagas() );

        return concursoRepresentation.build();
    }
}
