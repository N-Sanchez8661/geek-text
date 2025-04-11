package com.example.shopping_cart_api.dto;

public class DeleteBookResponse {
    private String message;

    public DeleteBookResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}