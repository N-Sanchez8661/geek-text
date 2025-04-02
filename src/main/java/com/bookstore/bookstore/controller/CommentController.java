package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.Comment;
import com.bookstore.bookstore.repository.BooksRepository;
import com.bookstore.bookstore.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BooksRepository booksRepository;

    // ✅ POST /comments/book/{bookId}
    @PostMapping("/book/{bookId}")
    public ResponseEntity<?> createComment(@PathVariable Long bookId, @RequestBody Comment commentRequest) {
        Optional<Books> optionalBook = booksRepository.findById(bookId);

        if (optionalBook.isEmpty()) {
            return ResponseEntity.status(404).body("Book with ID " + bookId + " not found.");
        }

        Comment comment = new Comment();
        comment.setBook(optionalBook.get());
        comment.setCommentText(commentRequest.getCommentText());
        comment.setUsername(commentRequest.getUsername());

        Comment saved = commentRepository.save(comment);
        return ResponseEntity.ok(saved);
    }

    // ✅ GET /comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // ✅ GET /comments/book/{bookId}
    @GetMapping("/book/{bookId}")
    public List<Comment> getCommentsByBook(@PathVariable Long bookId) {
        Optional<Books> book = booksRepository.findById(bookId);
        return book.map(commentRepository::findByBook).orElse(List.of());
    }
}
