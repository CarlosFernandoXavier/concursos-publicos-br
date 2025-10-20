package com.concursospublicosbr.security;

import com.concursospublicosbr.port.in.TokenServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final TokenServicePort tokenService;

    public JwtAuthenticationFilter(TokenServicePort tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
                String token = bearer.substring(7);
                if (tokenService.validar(token)) {
                    String username = tokenService.getSubject(token);
                    List<String> roles = tokenService.getRoles(token);
                    List<SimpleGrantedAuthority> authorities = roles == null ? null : roles.stream()
                            .filter(Objects::nonNull)
                            .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            log.warn("JWT filter error: {}", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
