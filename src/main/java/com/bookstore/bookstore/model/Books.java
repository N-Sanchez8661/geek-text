package com.bookstore.bookstore.model; // Keep your package name

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long bookId;

    @Column(nullable = false, length = 255)
    private String Title;

    @ManyToOne
    @JoinColumn(name = "AuthorID", nullable = false)
    private Authors author;

    @Column(nullable = false, length = 50)
    private String Genre;

    @Column(nullable = false)
    private int PublishedYear;

    @Column(nullable = false, length = 20)
    private String ISBN;

    @Column(nullable = false, precision = 10)
    private double Price;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int CopiesSold;

    @Column
    private String Description;

    @Column
    private String Publisher;

    // Constructors (default and parameterized)
    public Books() {}

    public Books(String title, Authors author, String genre, int publishedYear, String isbn, double price, int copiesSold, String description, String publisher) {
        this.Title = title;
        this.author = author;
        this.Genre = genre;
        this.PublishedYear = publishedYear;
        this.ISBN = isbn;
        this.Price = price;
        this.CopiesSold = copiesSold;
        this.Description = description;
        this.Publisher = publisher;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getPublishedYear() {
        return PublishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        PublishedYear = publishedYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getCopiesSold() {
        return CopiesSold;
    }

    public void setCopiesSold(int copiesSold) {
        CopiesSold = copiesSold;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}