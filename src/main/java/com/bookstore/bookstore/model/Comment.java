package com.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @Column(nullable = false, length = 1000)
    private String commentText;

    private String username;

    public Comment() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
