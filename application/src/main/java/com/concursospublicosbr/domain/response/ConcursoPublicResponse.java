package com.concursospublicosbr.domain.response;

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
public class ConcursoPublicResponse {
    private String estado;
    private String uf;
    @JsonProperty("concursos_abertos")
    private List<ConcursoResponse> concursosAbertos;
    @JsonProperty("concursos_previstos")
    private List<ConcursoResponse> concursosPrevistos;
    @JsonProperty("message")
    private String mensagem;
}
