package com.bookstore.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBook(Books book);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.book = :book")
    Double findAverageScoreByBook(@Param("book") Books book);

    @Query("SELECT r.book FROM Rating r GROUP BY r.book HAVING AVG(r.score) >= :minRating ORDER BY AVG(r.score) DESC")
    List<Books> findBooksByMinAverageRatingSorted(@Param("minRating") double minRating);
}
