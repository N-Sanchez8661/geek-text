package com.bookstore.bookstore.model;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "wishlist_items")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishlistItemId;

    @ManyToOne
    @JoinColumn(name = "wishlistId", nullable = false)
    @JsonIgnore  // Prevents recursion when fetching the wishlist
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Books book;

    // Getters and Setters
    public long getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(long wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book=book;
}
}