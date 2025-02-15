package com.bookstore.bookstore;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    @Column(nullable = false, length = 16)
    private String cardNumber;
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    @Column(nullable = false)
    private Date expiryDate;
    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(nullable = false, unique = true)
    private Integer CVV;
    public Integer getCVV() {
        return CVV;
    }
    public void setCVV(Integer CVV) {
        this.CVV = CVV;
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "userName", nullable = false)
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    public CreditCard() {}

    public CreditCard(String cardNumber, Date expiryDate, Integer CVV, User user) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
        this.user = user;
    }

}
