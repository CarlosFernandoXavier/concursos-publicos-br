package com.concursospublicosbr.stub;

import com.concursospublicosbr.domain.model.Concurso;

public class ConcursoStub {

    public static Concurso getConcursoAberto() {
        Concurso concurso = new Concurso();
        concurso.setOrgao("ARSESP");
        concurso.setVagas("105");
        return concurso;
    }

    public static Concurso getConcursoAberto2() {
        Concurso concurso = new Concurso();
        concurso.setOrgao("AMAZUL");
        concurso.setVagas("59");
        return concurso;
    }

    public static Concurso getConcursoPrevisto() {
        Concurso concurso = new Concurso();
        concurso.setOrgao("Banco do Brasil previsto");
        concurso.setVagas("7.200");
        return concurso;
    }

    public static Concurso getConcursoPrevisto2() {
        Concurso concurso = new Concurso();
        concurso.setOrgao("Receita Federal previsto");
        concurso.setVagas("Várias");
        return concurso;
    }
}
