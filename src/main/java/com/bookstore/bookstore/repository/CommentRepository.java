package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.Comment;
import com.bookstore.bookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBook(Books book);
}
