package com.bookstore.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BookstoreApplication.class, args);
        } catch (Exception e) {
            System.err.println("Application startup failed:");
            e.printStackTrace(); // Print the stack trace
        }
    }
}