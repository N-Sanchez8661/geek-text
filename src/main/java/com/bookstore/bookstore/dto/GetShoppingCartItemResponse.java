package com.bookstore.bookstore.dto;

public class GetShoppingCartItemResponse {

    private Long cartItemId;
    private long bookId;
    private String title;
    private double price;
    private String genre;
    private int publishedYear;
    private String isbn;

    // Constructors
    public GetShoppingCartItemResponse() {}

    public GetShoppingCartItemResponse(Long cartItemId, long bookId, String title, double price, String genre, int publishedYear, String isbn) {
        this.cartItemId = cartItemId;
        this.bookId = bookId;
        this.title = title;
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

    @Override
    public String toString() {
        return "GetShoppingCartItemResponse{" +
                "cartItemId=" + cartItemId +
                ", bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", publishedYear=" + publishedYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
