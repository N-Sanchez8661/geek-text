package com.example.shopping_cart_api.service;

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBookByID(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));
    }

    public static class BookNotFoundException extends IllegalArgumentException {
        public BookNotFoundException(String message) {
            super(message);
        }
    }
}