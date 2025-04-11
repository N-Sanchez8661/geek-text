package com.example.shopping_cart_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ShoppingCartItem")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    @JsonManagedReference
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(name = "quantity", nullable = false)
    private int quantity = 1;

    public ShoppingCartItem() {}

    public ShoppingCartItem(ShoppingCart shoppingCart, Book book, int quantity) {
        this.shoppingCart = shoppingCart;
        this.book = book;
        this.quantity = quantity;
    }

    // Getters and setters for cartItemId, shoppingCart, book, and quantity

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}