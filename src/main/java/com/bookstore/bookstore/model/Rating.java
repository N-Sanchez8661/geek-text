package com.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int score;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    public Rating() {}

    public Rating(int score, Books book) {
        this.score = score;
        this.book = book;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }
}
