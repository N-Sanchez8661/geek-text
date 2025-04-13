package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Books getBookByID(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));
    }

    public static class BookNotFoundException extends IllegalArgumentException {
        public BookNotFoundException(String message) {
            super(message);
        }
    }
}