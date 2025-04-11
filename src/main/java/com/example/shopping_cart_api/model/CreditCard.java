package com.example.shopping_cart_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditCardId;

    @Column(nullable = false, length = 16)
    private String cardNumber;

    @Column(name = "expiryDate", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    private Date expiryDate;

    @Column(nullable = false)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    public Long getCreditCardId() {
        return creditCardId;
    }
    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    public CreditCard() {}

    public CreditCard(String cardNumber, Date expiryDate, String cvv, User user) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.user = user;
    }

}
