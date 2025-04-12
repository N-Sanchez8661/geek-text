package com.bookstore.bookstore.dto;
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
//this class is not being used but is kept in case I want to implement a response  for adding a book