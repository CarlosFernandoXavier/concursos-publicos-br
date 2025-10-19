package com.concursospublicosbr.mapper;

import com.concursospublicosbr.domain.model.Concurso;
import com.concursospublicosbr.domain.model.ConcursoPublico;
import com.concursospublicosbr.domain.response.ConcursoPublicResponse;
import com.concursospublicosbr.domain.response.ConcursoResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-19T11:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.19 (Ubuntu)"
)
public class ConcursoPublicoMapperImpl implements ConcursoPublicoMapper {

    private final ConcursoMapper concursoMapper = Mappers.getMapper( ConcursoMapper.class );

    @Override
    public ConcursoPublico toConcursoPublico(ConcursoPublicResponse concursoPublicResponse) {
        if ( concursoPublicResponse == null ) {
            return null;
        }

        ConcursoPublico.ConcursoPublicoBuilder concursoPublico = ConcursoPublico.builder();

        concursoPublico.estado( concursoPublicResponse.getEstado() );
        concursoPublico.uf( concursoPublicResponse.getUf() );
        concursoPublico.concursosAbertos( concursoResponseListToConcursoList( concursoPublicResponse.getConcursosAbertos() ) );
        concursoPublico.concursosPrevistos( concursoResponseListToConcursoList( concursoPublicResponse.getConcursosPrevistos() ) );
        concursoPublico.mensagem( concursoPublicResponse.getMensagem() );

        return concursoPublico.build();
    }

    protected List<Concurso> concursoResponseListToConcursoList(List<ConcursoResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Concurso> list1 = new ArrayList<Concurso>( list.size() );
        for ( ConcursoResponse concursoResponse : list ) {
            list1.add( concursoMapper.toConcurso( concursoResponse ) );
        }

        return list1;
    }
}
