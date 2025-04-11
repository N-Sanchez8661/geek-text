package com.example.shopping_cart_api.dto;
public class AddBookResponse {
    private String message;

    public AddBookResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}