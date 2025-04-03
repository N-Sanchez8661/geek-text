package com.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    public Comment() {}

    public Comment(String content, Books book) {
        this.content = content;
        this.book = book;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }
}
