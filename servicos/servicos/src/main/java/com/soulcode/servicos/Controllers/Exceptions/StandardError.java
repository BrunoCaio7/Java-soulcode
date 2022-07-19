package com.soulcode.servicos.Controllers.Exceptions;

import java.time.Instant;

public class StandardError {
    //  1 passo criar atributos
    private Instant timestamp;
    private Integer status;
    private String error;
    private String trace;
    private String message;
    private String path;

    // 2 passo criar construtor
    public StandardError(){}

    // 3 passo criar gatters e seters


    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
