package com.concursospublicosbr.port.in;

import com.concursospublicosbr.domain.model.Token;

import java.util.List;

public interface TokenServicePort {
    String gerarToken(Token token);

    boolean validar(String jwt);

    String getSubject(String jwt);

    List<String> getRoles(String jwt);
}
