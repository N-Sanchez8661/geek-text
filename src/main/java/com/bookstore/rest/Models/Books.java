package com.bookstore.rest.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private long BookID;

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

    public long getBookID() {
        return BookID;
    }

    public void setBookID(long bookID) {
        BookID = bookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    public void setCopiesSold(int copiesSold) {CopiesSold = copiesSold;}

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {Description = description;}

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public String getPublisher() { return Publisher; }

    public void setPublisher(String publisher) {Publisher = publisher; }

}
