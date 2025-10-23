package com.concursospublicosbr.in.web;

import com.concursospublicosbr.exception.BusinessException;
import com.concursospublicosbr.api.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        log.warn("BusinessException: {}", ex.getMessage());
        ErrorResponse body = new ErrorResponse();
        body.setMessage(ex.getMessage());
        body.setError("BUSINESS_ERROR");
        body.setStatus(HttpStatus.BAD_REQUEST.value());
        body.setTimestamp(OffsetDateTime.now());
        body.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        log.error("ConstraintViolationException: {}", ex.getMessage());
        ErrorResponse body = new ErrorResponse();
        body.setMessage("Uf deve ser preenchida por ao menos uma das siglas a seguir: ac, al, ap, am, ba, ce, df, es, go, ma, mt, ms, mg, pa, pb, pr, pe, pi, rj, rn, rs, ro, rr, sc, sp, se, to");
        body.setError("VIOLATION_ERROR");
        body.setStatus(HttpStatus.BAD_REQUEST.value());
        body.setTimestamp(OffsetDateTime.now());
        body.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) {
        log.error("HttpClientErrorException {}", ex.getMessage());
        ErrorResponse body = new ErrorResponse();
        body.setMessage("Unidade Federativa (uf) não encontrada");
        body.setError("NOT_FOUND_ERROR");
        body.setStatus(HttpStatus.NOT_FOUND.value());
        body.setTimestamp(OffsetDateTime.now());
        body.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception: {}", ex.getMessage());
        ErrorResponse body = new ErrorResponse();
        body.setMessage("Erro interno no servidor");
        body.setError("INTERNAL_ERROR");
        body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.setTimestamp(OffsetDateTime.now());
        body.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}

