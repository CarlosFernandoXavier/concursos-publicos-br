package com.concursospublicosbr.in.web;

import java.time.OffsetDateTime;

public class ErrorResponse {
    private final String message;
    private final String error;
    private final OffsetDateTime timestamp;

    public ErrorResponse(String message, String error) {
        this.message = message;
        this.error = error;
        this.timestamp = OffsetDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
