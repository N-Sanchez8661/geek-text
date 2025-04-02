package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Rating;
import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.repository.RatingRepository;
import com.bookstore.bookstore.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping("/book/{bookId}")
    public Rating createRating(@PathVariable Long bookId, @RequestBody Rating rating) {
        Books book = booksRepository.findById(bookId).orElseThrow();
        rating.setBook(book);
        return ratingRepository.save(rating);
    }

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
