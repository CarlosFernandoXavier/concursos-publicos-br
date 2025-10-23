package com.concursospublicosbr.domain.model;

import java.util.List;

public class ConcursoPublico {
    private String estado;
    private String uf;
    private List<Concurso> concursosAbertos;
    private List<Concurso> concursosPrevistos;
    private String mensagem;

    public ConcursoPublico() {
    }

    public ConcursoPublico(String estado, String uf, List<Concurso> concursosAbertos,
                            List<Concurso> concursosPrevistos, String mensagem) {
        this.estado = estado;
        this.uf = uf;
        this.concursosAbertos = concursosAbertos;
        this.concursosPrevistos = concursosPrevistos;
        this.mensagem = mensagem;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Concurso> getConcursosAbertos() {
        return concursosAbertos;
    }

    public void setConcursosAbertos(List<Concurso> concursosAbertos) {
        this.concursosAbertos = concursosAbertos;
    }

    public List<Concurso> getConcursosPrevistos() {
        return concursosPrevistos;
    }

    public void setConcursosPrevistos(List<Concurso> concursosPrevistos) {
        this.concursosPrevistos = concursosPrevistos;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
