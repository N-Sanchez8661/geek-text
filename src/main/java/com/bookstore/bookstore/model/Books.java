package com.bookstore.bookstore.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private long bookID;

    @Column(nullable = false , length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AuthorID" , nullable = false)
    private Authors author;

    @Column(nullable = false , length = 50)
    private String genre;

    @Column(nullable = false)
    private int publishedYear;

    @Column(nullable = false , length = 20)
    private String ISBN;

    @Column(nullable = false , precision = 10)
    private double price;

    @Column(nullable = false , columnDefinition = "INTEGER DEFAULT 0")
    private int copiesSold;

    @Column
    private String description;

    @Column
    private String publisher;


    public long getBookID() {
        return bookID;
    }
    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
    public void setCopiesSold(int copiesSold) {this.copiesSold = copiesSold;}

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}

    public Authors getAuthor() {
        return author;
    }
    public void setAuthor(Authors author) {
        this.author = author;
    }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) {this.publisher = publisher; }

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    public List<Rating> getRatings() {
        return ratings;
    }
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
