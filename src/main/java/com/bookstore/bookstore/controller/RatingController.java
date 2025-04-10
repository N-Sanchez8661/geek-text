package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.AverageRatingDTO;
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

    // POST a new rating
    @PostMapping("/{bookId}")
    public Rating addRating(@PathVariable Long bookId, @RequestBody Rating rating) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        rating.setBook(book);
        return ratingRepository.save(rating);
    }

    // GET all ratings for a book
    public List<Rating> getRatings(@PathVariable Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return ratingRepository.findByBook(book);
    }

    // GET average rating for a book
    @GetMapping("/average/{bookId}")
    public AverageRatingDTO getAverageRating(@PathVariable Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Double average = ratingRepository.findAverageScoreByBook(book);
        if (average == null) {
            average = 0.0;
        }
        return new AverageRatingDTO(bookId, Math.round(average * 100.0) / 100.0);
    }
}
