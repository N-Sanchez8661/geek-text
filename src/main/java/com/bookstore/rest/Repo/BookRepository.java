package com.bookstore.rest.Repo;

import com.bookstore.rest.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByISBN(String ISBN);

    @Query(value = "SELECT * FROM books WHERE AuthorID = :authorId", nativeQuery = true)
    List<Books> findByAuthorId(@Param("authorId") int authorId);
}
