package com.bookstore.bookstore.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String homeAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCard> creditCardList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    // Constructors
    public User() {
    }

    public User(String username, String password, String name, String email, String homeAddress) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.homeAddress = homeAddress;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public List<CreditCard> getCreditCardList() {
      return creditCardList;
   }

   public void setCreditCardList(List<CreditCard> creditCardList) {
        this.creditCardList = creditCardList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            if (this.shoppingCart != null) {
                this.shoppingCart.setUser(null);
            }
        } else {
            shoppingCart.setUser(this);
        }
        this.shoppingCart = shoppingCart;
    }
}