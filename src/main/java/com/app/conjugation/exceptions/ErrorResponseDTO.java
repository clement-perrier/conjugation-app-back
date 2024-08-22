package com.app.conjugation.exceptions;

public class ErrorResponseDTO {
    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
