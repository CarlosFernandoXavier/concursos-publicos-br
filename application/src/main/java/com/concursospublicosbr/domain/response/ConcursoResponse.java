package com.concursospublicosbr.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConcursoResponse {
    @JsonProperty("Órgão")
    private String orgao;
    @JsonProperty("Vagas")
    private String vagas;

    public ConcursoResponse() {
    }

    public ConcursoResponse(String orgao, String vagas) {
        this.orgao = orgao;
        this.vagas = vagas;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public String getVagas() {
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;
    }
}
