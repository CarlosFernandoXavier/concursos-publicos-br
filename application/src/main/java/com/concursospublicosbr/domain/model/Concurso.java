package com.concursospublicosbr.domain.model;

public class Concurso {
    private String orgao;
    private String vagas;

    public Concurso() {
    }

    public Concurso(String orgao, String vagas) {
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
