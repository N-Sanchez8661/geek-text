package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBook(Books book);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.book = :book")
    Double findAverageScoreByBook(@Param("book") Books book);
}
