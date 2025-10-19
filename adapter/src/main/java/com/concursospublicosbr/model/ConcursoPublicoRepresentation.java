package com.concursospublicosbr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConcursoPublicoRepresentation {
    private String estado;
    private String uf;
    private List<ConcursoRepresentation> concursosAbertos;
    private List<ConcursoRepresentation> concursosPrevistos;
    private String mensagem;
}
