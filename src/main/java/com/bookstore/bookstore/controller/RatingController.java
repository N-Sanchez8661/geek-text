package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Rating;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/{bookId}")
    public Rating addRating(@PathVariable Long bookId, @RequestBody Rating rating) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        rating.setBook(book);
        return ratingRepository.save(rating);
    }

    @GetMapping("/{bookId}")
    public List<Rating> getRatings(@PathVariable Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return ratingRepository.findByBook(book);
    }
}
