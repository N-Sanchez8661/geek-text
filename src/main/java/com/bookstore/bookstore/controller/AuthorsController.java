package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Authors;
import com.bookstore.bookstore.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsRepository authorsRepository;

    // ✅ POST /authors
    @PostMapping
    public ResponseEntity<Authors> createAuthor(@RequestBody Authors author) {
        Authors saved = authorsRepository.save(author);
        return ResponseEntity.ok(saved);
    }

    // ✅ GET /authors
    @GetMapping
    public List<Authors> getAllAuthors() {
        return authorsRepository.findAll();
    }

    // ✅ GET /authors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Optional<Authors> author = authorsRepository.findById(id);
        return author.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Author not found"));
    }

    // ✅ DELETE /authors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        if (!authorsRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Author not found");
        }
        authorsRepository.deleteById(id);
        return ResponseEntity.ok("Author deleted");
    }
}
