package com.example.shopping_cart_api.dto;

import com.example.shopping_cart_api.model.Authors;

public class GetShoppingCartItemResponse {

    //private Authors author;
    private Long cartItemId;
    private long bookId; // Changed to long
    private String title; // Changed to title
    private int quantity;
    private double price;
    private String genre; // Added genre
    private int publishedYear; // Added publishedYear
    private String isbn; //Added isbn

    // Constructors
    public GetShoppingCartItemResponse() {}

    public GetShoppingCartItemResponse(Long cartItemId, long bookId, String title, int quantity, double price, String genre, int publishedYear, String isbn) {
        this.cartItemId = cartItemId;
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }

    // Getters and Setters
    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public long getbookId() { // Changed to long
        return bookId;
    }

    public void setbookId(long bookId) { // Changed to long
        this.bookId = bookId;
    }

    public String getTitle() { // Changed to title
        return title;
    }

    public void setTitle(String title) { // Changed to title
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    // Optionally, you can add a toString() method for debugging
    @Override
    public String toString() {
        return "GetShoppingCartItemResponse{" +
                "cartItemId=" + cartItemId +
                ", bookId=" + bookId +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", publishedYear=" + publishedYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}