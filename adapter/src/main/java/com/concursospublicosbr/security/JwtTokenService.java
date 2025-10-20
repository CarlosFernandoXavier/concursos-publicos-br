package com.concursospublicosbr.security;

import com.concursospublicosbr.domain.model.Token;
import com.concursospublicosbr.port.in.TokenServicePort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JwtTokenService implements TokenServicePort {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:3600}")
    private long expirationSeconds;

    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String gerarToken(Token token) {
        String subject = (token.getUsuario() == null || token.getUsuario().isBlank()) ? "user" : token.getUsuario();
        List<String> roles = (token.getPerfis() == null || token.getPerfis().isEmpty()) ? Collections.singletonList("USER") : token.getPerfis();
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationSeconds * 1000);
        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validar(String jwt) {
        try {
            getClaims(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSubject(String jwt) {
        return getClaims(jwt).getSubject();
    }

    @Override
    public List<String> getRoles(String jwt) {
        Object rolesObj = getClaims(jwt).get("roles");
        if (rolesObj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) rolesObj;
            return list.stream().map(Objects::toString).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
