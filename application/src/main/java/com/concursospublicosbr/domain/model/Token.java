package com.concursospublicosbr.domain.model;

import java.util.List;

public class Token {
    private String usuario;
    private List<String> perfis;

    public Token(String usuario, List<String> perfis) {
        this.usuario = usuario;
        this.perfis = perfis;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<String> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<String> perfis) {
        this.perfis = perfis;
    }
}
