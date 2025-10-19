package com.concursospublicosbr.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConcursoResponse {
    @JsonProperty("Órgão")
    private String orgao;
    @JsonProperty("Vagas")
    private String vagas;
}
