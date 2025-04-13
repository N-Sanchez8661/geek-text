package com.bookstore.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.bookstore.model.Books;

import jakarta.transaction.Transactional;

public interface BookRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByIsbn(String isbn);

    @Query(value = "SELECT * FROM books WHERE AuthorID = :authorId", nativeQuery = true)
    List<Books> findByAuthorId(@Param("authorId") int authorId);

    @Query("SELECT b FROM Books b WHERE b.genre = :genre")
    List<Books> findByGenre(@Param("genre") String genre);

    @Modifying
    @Transactional
    @Query("UPDATE Books b SET b.price = ROUND(b.price * :discount, 2) WHERE b.publisher = :publisher")
    int updatePriceByPublisher(@Param("publisher") String publisher, @Param("discount") double discount);

}
