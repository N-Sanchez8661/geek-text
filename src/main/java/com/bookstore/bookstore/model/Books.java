package com.bookstore.bookstore.model;

import com.bookstore.bookstore.model.Authors;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long bookId;

    @Column(nullable = false, length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private Authors author;

    @Column(nullable = false, length = 50)
    private String genre;

    @Column(nullable = false)
    private int publishedYear;

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(nullable = false, precision = 10)
    private double price;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int copiesSold;

    @Column
    private String description;

    @Column
    private String publisher;

    // Constructors
    public Books() {}

    public Books(String title, Authors author, String genre, int publishedYear, String isbn, double price, int copiesSold, String description, String publisher) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.price = price;
        this.copiesSold = copiesSold;
        this.description = description;
        this.publisher = publisher;
    }

    // Getters and Setters
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }
}
