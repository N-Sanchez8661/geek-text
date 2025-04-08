package com.bookstore.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.bookstore.model.Books;

public interface BookRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByISBN(String ISBN);
    
    @Query(value = "SELECT * FROM books WHERE AuthorID = :authorId", nativeQuery = true)
    List<Books> findByAuthorId(@Param("authorId") int authorId);

    @Query("SELECT b FROM Books b JOIN b.ratings r GROUP BY b HAVING AVG(r.score) >= :minRating ORDER BY AVG(r.score) DESC")
    List<Books> findBooksByAverageRating(@Param("minRating") int minRating);
}
