package com.geektext.controllers;

import com.geektext.models.Rating;
import com.geektext.models.Book;
import com.geektext.models.User;
import com.geektext.repositories.BookRepository;
import com.geektext.repositories.UserRepository;
import com.geektext.repositories.RatingRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RatingController(RatingRepository ratingRepository,
                            UserRepository userRepository,
                            BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody Rating rating) {
        Long userId = rating.getUser().getUserId();
        Long bookId = rating.getBook().getBookId();

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid userId or bookId");
        }

        rating.setUser(userOpt.get());
        rating.setBook(bookOpt.get());

        Rating savedRating = ratingRepository.save(rating);
        return ResponseEntity.ok(savedRating);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Rating>> getRatingsByBook(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok(ratingRepository.findByBookBookId(bookId));
    }
}
