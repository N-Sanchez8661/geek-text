package com.example.shopping_cart_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(mappedBy = "user")
    private ShoppingCart shoppingCart;

    public Long getUserId() { // Getter for userId (ESSENTIAL)
        return userId;
    }

    public ShoppingCart getShoppingCart() { 
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
