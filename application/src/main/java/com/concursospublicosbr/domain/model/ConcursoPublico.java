package com.concursospublicosbr.domain.model;

import com.concursospublicosbr.domain.response.ConcursoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConcursoPublico {
    private String estado;
    private String uf;
    private List<Concurso> concursosAbertos;
    private List<Concurso> concursosPrevistos;
    private String mensagem;
}
