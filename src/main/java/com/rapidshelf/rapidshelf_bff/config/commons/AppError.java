package com.rapidshelf.rapidshelf_bff.config.commons;


import java.util.Map;

public class AppError {
    private String message;
    private int status;
    private Map<String, String> details;

    public AppError(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public AppError(String message, int status, Map<String, String> details) {
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}