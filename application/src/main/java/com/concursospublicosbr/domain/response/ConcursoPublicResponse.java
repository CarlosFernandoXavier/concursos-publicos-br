package com.concursospublicosbr.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConcursoPublicResponse {
    private String estado;
    private String uf;
    @JsonProperty("concursos_abertos")
    private List<ConcursoResponse> concursosAbertos;
    @JsonProperty("concursos_previstos")
    private List<ConcursoResponse> concursosPrevistos;
    @JsonProperty("message")
    private String mensagem;

    public ConcursoPublicResponse() {
    }

    public ConcursoPublicResponse(String estado, String uf, List<ConcursoResponse> concursosAbertos,
                                  List<ConcursoResponse> concursosPrevistos, String mensagem) {
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

    public List<ConcursoResponse> getConcursosAbertos() {
        return concursosAbertos;
    }

    public void setConcursosAbertos(List<ConcursoResponse> concursosAbertos) {
        this.concursosAbertos = concursosAbertos;
    }

    public List<ConcursoResponse> getConcursosPrevistos() {
        return concursosPrevistos;
    }

    public void setConcursosPrevistos(List<ConcursoResponse> concursosPrevistos) {
        this.concursosPrevistos = concursosPrevistos;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
