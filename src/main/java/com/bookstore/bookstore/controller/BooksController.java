package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Authors;
import com.bookstore.bookstore.repository.BooksRepository;
import com.bookstore.bookstore.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    // ✅ POST /books
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Books bookRequest) {
        if (bookRequest.getAuthor() == null || bookRequest.getAuthor().getAuthorId() == null) {
            return ResponseEntity.badRequest().body("Author ID is required.");
        }

        Optional<Authors> author = authorsRepository.findById(bookRequest.getAuthor().getAuthorId());

        if (author.isEmpty()) {
            return ResponseEntity.status(404).body("Author not found.");
        }

        bookRequest.setAuthor(author.get());
        Books saved = booksRepository.save(bookRequest);
        return ResponseEntity.ok(saved);
    }

    // ✅ GET /books
    @GetMapping
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }
}
