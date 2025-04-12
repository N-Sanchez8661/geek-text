package com.bookstore.bookstore.dto;

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
//this class is not being used but is kept in case I want to implement a response for deleting a book