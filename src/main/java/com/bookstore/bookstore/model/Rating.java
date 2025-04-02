package com.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @Column(nullable = false)
    private int rating; // 1 to 5

    // Optional: you can link to user
    private String username;

    public Rating() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Books getBook() { return book; }
    public void setBook(Books book) { this.book = book; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
