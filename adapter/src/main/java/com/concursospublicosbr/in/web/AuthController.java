package com.concursospublicosbr.in.web;

import com.concursospublicosbr.api.AuthApi;
import com.concursospublicosbr.api.model.TokenRequestRepresentation;
import com.concursospublicosbr.api.model.TokenResponseRepresentation;
import com.concursospublicosbr.domain.model.Token;
import com.concursospublicosbr.mapper.TokenMapper;
import com.concursospublicosbr.port.in.TokenServicePort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {

    private final TokenMapper tokenMapper;
    private final TokenServicePort tokenService;

    @Override
    public ResponseEntity<TokenResponseRepresentation> generateToken(TokenRequestRepresentation request) {
        Token token = tokenMapper.toToken(request);
        String chaveToken = tokenService.gerarToken(token);
        TokenResponseRepresentation resp = new TokenResponseRepresentation(chaveToken);
        return ResponseEntity.ok(resp);
    }
}
