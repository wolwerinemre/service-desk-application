package com.simple.serviceDeskApplication.util.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private HttpStatus status;
    @JsonIgnore
    private HttpHeaders headers;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, HttpHeaders headers, Throwable ex) {
        this();
        this.status = status;
        this.headers = headers;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, HttpHeaders headers, String message, Throwable ex) {
        this();
        this.status = status;
        this.headers = headers;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}