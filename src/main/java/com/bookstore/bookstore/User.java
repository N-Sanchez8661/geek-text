package com.bookstore.bookstore;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public Long getUserID() {
        return userID;
    }

    @Column(nullable = false, unique = true)
    private String userName;
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    @Column(nullable = false)
    private String password;
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Column(nullable = true, unique = true)
    private String email;
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    @Column(nullable = true)
    private String address;
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    @OneToMany
    private List<CreditCard> creditCardList;


    public User() {}

    public User(Long userID, String userName, String password, String name, String email, String address) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
    }

}

