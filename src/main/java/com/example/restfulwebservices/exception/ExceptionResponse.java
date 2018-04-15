package com.example.restfulwebservices.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final LocalDateTime timestamp;
    private final String description;
    private final String message;

    public ExceptionResponse(LocalDateTime now, String message, String description) {
        this.timestamp = now;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
