package com.geektext.rest.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "Wishlist")
public class Wishlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlistId")
    private long wishlistId;


    @Column(name = "wishlistName", nullable = false)
    private String wishlistName;


    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    // @JsonIgnore
    // @JsonIgnoreProperties("wishlists") // Prevents infinite loop
    @JsonIgnoreProperties(value = "wishlists", allowSetters = true) // ✅ Prevents recursion but allows setting user
    private User user;


    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WishlistItem> wishlistItems;


    // Getters and Setters
    public long getWishlistId() { return wishlistId; }
    public void setWishlistId(long wishlistId) { this.wishlistId = wishlistId; }
    public String getWishlistName() { return wishlistName; }
    public void setWishlistName(String wishlistName) { this.wishlistName = wishlistName; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<WishlistItem> getWishlistItems() { return wishlistItems; }
    public void setWishlistItems(List<WishlistItem> wishlistItems) { this.wishlistItems = wishlistItems; }
}
