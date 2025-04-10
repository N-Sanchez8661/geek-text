package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.CommentResponseDTO;
import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Comment;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/{bookId}")
    public CommentResponseDTO addComment(@PathVariable Long bookId, @RequestBody Comment comment) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        comment.setBook(book);
        Comment saved = commentRepository.save(comment);
        return new CommentResponseDTO(saved.getId(), saved.getContent(), book);
    }

    @GetMapping("/{bookId}")
    public List<CommentResponseDTO> getComments(@PathVariable Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        List<Comment> comments = commentRepository.findByBook(book);
        return comments.stream()
                .map(c -> new CommentResponseDTO(c.getId(), c.getContent(), book))
                .collect(Collectors.toList());
    }
}
