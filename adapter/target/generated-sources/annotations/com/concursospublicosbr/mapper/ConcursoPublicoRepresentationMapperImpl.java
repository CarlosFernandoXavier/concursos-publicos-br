package com.concursospublicosbr.mapper;

import com.concursospublicosbr.domain.model.Concurso;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.model.ConcursoPublicoRepresentation;
import com.concursospublicosbr.model.ConcursoRepresentation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-19T11:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.19 (Ubuntu)"
)
public class ConcursoPublicoRepresentationMapperImpl implements ConcursoPublicoRepresentationMapper {

    private final ConcursoRepresentationMapper concursoRepresentationMapper = Mappers.getMapper( ConcursoRepresentationMapper.class );

    @Override
    public ConcursoPublicoRepresentation toConcursoPublicoRepresentation(ConcursoPublico concursoPublico) {
        if ( concursoPublico == null ) {
            return null;
        }

        ConcursoPublicoRepresentation.ConcursoPublicoRepresentationBuilder concursoPublicoRepresentation = ConcursoPublicoRepresentation.builder();

        concursoPublicoRepresentation.estado( concursoPublico.getEstado() );
        concursoPublicoRepresentation.uf( concursoPublico.getUf() );
        concursoPublicoRepresentation.concursosAbertos( concursoListToConcursoRepresentationList( concursoPublico.getConcursosAbertos() ) );
        concursoPublicoRepresentation.concursosPrevistos( concursoListToConcursoRepresentationList( concursoPublico.getConcursosPrevistos() ) );
        concursoPublicoRepresentation.mensagem( concursoPublico.getMensagem() );

        return concursoPublicoRepresentation.build();
    }

    protected List<ConcursoRepresentation> concursoListToConcursoRepresentationList(List<Concurso> list) {
        if ( list == null ) {
            return null;
        }

        List<ConcursoRepresentation> list1 = new ArrayList<ConcursoRepresentation>( list.size() );
        for ( Concurso concurso : list ) {
            list1.add( concursoRepresentationMapper.toConcursoRepresentation( concurso ) );
        }

        return list1;
    }
}
