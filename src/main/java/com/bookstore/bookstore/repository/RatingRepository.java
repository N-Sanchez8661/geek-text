package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.Rating;
import com.bookstore.bookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBook(Books book);
}
