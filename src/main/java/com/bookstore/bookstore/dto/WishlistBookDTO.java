package com.bookstore.bookstore.dto;

public class WishlistBookDTO {

    private Long wishlistId;
    private Long bookId;

    // Getters and Setters
    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId =bookId;
    }
}