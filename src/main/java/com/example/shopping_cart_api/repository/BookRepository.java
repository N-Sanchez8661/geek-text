package com.example.shopping_cart_api.repository;

import com.example.shopping_cart_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN(String ISBN);

    @Query(
            value = "SELECT * FROM books WHERE AuthorID = :authorId",
            nativeQuery = true
    )
    List<Book> findByAuthorId(@Param("authorId") int authorId);
}
