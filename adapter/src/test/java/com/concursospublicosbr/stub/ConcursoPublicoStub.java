package com.concursospublicosbr.stub;

import com.concursospublicosbr.domain.model.Concurso;
import com.concursospublicosbr.domain.model.ConcursoPublico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcursoPublicoStub {

    public static ConcursoPublico getConcursoPublico() {
        ConcursoPublico concursoPublico = new ConcursoPublico();
        concursoPublico.setEstado("São Paulo");
        concursoPublico.setUf("sp");
        List<Concurso> concursosAbertos = new ArrayList<>();
        concursosAbertos.add(ConcursoStub.getConcursoAberto());
        concursosAbertos.add(ConcursoStub.getConcursoAberto2());
        concursoPublico.setConcursosAbertos(concursosAbertos);
        List<Concurso> concursosPrevistos = new ArrayList<>();
        concursosPrevistos.add(ConcursoStub.getConcursoPrevisto());
        concursosPrevistos.add(ConcursoStub.getConcursoPrevisto2());
        concursoPublico.setConcursosPrevistos(concursosPrevistos);
        return concursoPublico;
    }

    public static ConcursoPublico getConcursoPublicoServicoIndiponivel() {
        ConcursoPublico concursoPublico = new ConcursoPublico();
        concursoPublico.setEstado("São Paulo");
        concursoPublico.setUf("sp");
        concursoPublico.setConcursosAbertos(Collections.emptyList());
        concursoPublico.setConcursosPrevistos(Collections.emptyList());
        concursoPublico.setMensagem("Serviço indisponível no momento");
        return concursoPublico;
    }
}
