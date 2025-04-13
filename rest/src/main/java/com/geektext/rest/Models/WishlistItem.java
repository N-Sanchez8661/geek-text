package com.geektext.rest.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(
   name = "WishlistItem"
)
public class WishlistItem {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   @Column(
      name = "wishlistItemId"
   )
   private long wishlistItemId;
   @ManyToOne
   @JoinColumn(
      name = "wishlistId",
      nullable = false
   )

   @JsonIgnore  // ✅ This prevents recursion when fetching wishlists
   private Wishlist wishlist;
   @ManyToOne
   @JoinColumn(
      name = "bookId",
      nullable = false
   )
   private Book book;


   public WishlistItem() {
   }


   public long getWishlistItemId() {
      return this.wishlistItemId;
   }


   public void setWishlistItemId(long wishlistItemId) {
      this.wishlistItemId = wishlistItemId;
   }


   public Wishlist getWishlist() {
      return this.wishlist;
   }


   public void setWishlist(Wishlist wishlist) {
      this.wishlist = wishlist;
   }


   public Book getBook() {
      return this.book;
   }


   public void setBook(Book book) {
      this.book = book;
   }
}


