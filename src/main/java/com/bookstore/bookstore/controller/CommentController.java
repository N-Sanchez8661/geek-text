package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Comment;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/{bookId}")
    public Comment addComment(@PathVariable Long bookId, @RequestBody Comment comment) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        comment.setBook(book);
        return commentRepository.save(comment);
    }

    @GetMapping("/{bookId}")
    public List<Comment> getComments(@PathVariable Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return commentRepository.findByBook(book);
    }
}
