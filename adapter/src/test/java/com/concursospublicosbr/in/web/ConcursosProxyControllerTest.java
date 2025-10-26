package com.concursospublicosbr.in.web;

import com.concursospublicosbr.port.in.ConcursosPublicosServicePort;
import com.concursospublicosbr.stub.ConcursoPublicoStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // desabilita segurança para focar na validação
class ConcursosProxyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcursosPublicosServicePort concursosPublicosService;

    @Test
    @DisplayName("Deve retornar 200 para UF maiúscula válida")
    void shouldReturn200ForValidUf() throws Exception {
        Mockito.when(concursosPublicosService.getConcursosPublicosDisponiveis(eq("SP")))
                .thenReturn(ConcursoPublicoStub.getConcursoPublico());

        String esperado = "{\"estado\":\"São Paulo\",\"uf\":\"sp\",\"concursosAbertos\":[{\"orgao\":\"ARSESP\",\"vagas\":\"105\"},{\"orgao\":\"AMAZUL\",\"vagas\":\"59\"}],\"concursosPrevistos\":[{\"orgao\":\"Banco do Brasil previsto\",\"vagas\":\"7.200\"},{\"orgao\":\"Receita Federal previsto\",\"vagas\":\"Várias\"}],\"mensagem\":null}";
        mockMvc.perform(get("/api/concursos/concursos-publicos")
                .param("uf", "SP")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(esperado, true));

        Mockito.verify(concursosPublicosService, Mockito.times(1)).getConcursosPublicosDisponiveis("SP");
    }

    @Test
    @DisplayName("Deve retornar 200 para UF minúscula válida")
    void shouldReturn200ForValidUf2() throws Exception {
        Mockito.when(concursosPublicosService.getConcursosPublicosDisponiveis(eq("sp")))
                .thenReturn(ConcursoPublicoStub.getConcursoPublico());

        String esperado = "{\"estado\":\"São Paulo\",\"uf\":\"sp\",\"concursosAbertos\":[{\"orgao\":\"ARSESP\",\"vagas\":\"105\"},{\"orgao\":\"AMAZUL\",\"vagas\":\"59\"}],\"concursosPrevistos\":[{\"orgao\":\"Banco do Brasil previsto\",\"vagas\":\"7.200\"},{\"orgao\":\"Receita Federal previsto\",\"vagas\":\"Várias\"}],\"mensagem\":null}";
        mockMvc.perform(get("/api/concursos/concursos-publicos")
                .param("uf", "sp")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(esperado, true));

        Mockito.verify(concursosPublicosService, Mockito.times(1)).getConcursosPublicosDisponiveis("sp");
    }

    @Test
    @DisplayName("Deve retornar 200, mas com dados indisponíveis para UF")
    void shouldReturn200ForServiceUnavailable() throws Exception {
        Mockito.when(concursosPublicosService.getConcursosPublicosDisponiveis(eq("sp")))
                .thenReturn(ConcursoPublicoStub.getConcursoPublicoServicoIndiponivel());

        String esperado = "{\"estado\":\"São Paulo\",\"uf\":\"sp\",\"concursosAbertos\":[],\"concursosPrevistos\":[],\"mensagem\":\"Serviço indisponível no momento\"}";
        mockMvc.perform(get("/api/concursos/concursos-publicos")
                .param("uf", "sp")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(esperado, true));

        Mockito.verify(concursosPublicosService, Mockito.times(1)).getConcursosPublicosDisponiveis("sp");
    }

    @Test
    @DisplayName("Deve retornar 400 para UF de tamanho inválido")
    void shouldReturn400ForInvalidLength() throws Exception {
        mockMvc.perform(get("/api/concursos/concursos-publicos")
                .param("uf", "S")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("VIOLATION_ERROR"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("Deve retornar 400 para UF fora da lista permitida (ex.: XX)")
    void shouldReturn400ForInvalidValue() throws Exception {
        mockMvc.perform(get("/api/concursos/concursos-publicos")
                .param("uf", "XX")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("VIOLATION_ERROR"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Deve retornar 400 quando UF está ausente")
    void shouldReturn400WhenUfMissing() throws Exception {
        mockMvc.perform(get("/api/concursos/concursos-publicos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}