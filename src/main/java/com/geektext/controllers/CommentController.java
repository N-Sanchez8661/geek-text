package com.geektext.controllers;

import com.geektext.models.Comment;
import com.geektext.models.User;
import com.geektext.models.Book;
import com.geektext.repositories.CommentRepository;
import com.geektext.repositories.UserRepository;
import com.geektext.repositories.BookRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CommentController(CommentRepository commentRepository,
                             UserRepository userRepository,
                             BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        Long userId = comment.getUser().getUserId();
        Long bookId = comment.getBook().getBookId();

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid userId or bookId");
        }

        comment.setUser(userOpt.get());
        comment.setBook(bookOpt.get());

        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Comment>> getCommentsByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(commentRepository.findByBookBookId(bookId));
    }
}
