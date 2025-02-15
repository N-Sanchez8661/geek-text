package com.bookstore.bookstore;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(nullable = false, length = 16)
    private String cardNumber;

    @Column(name = "expiryDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(nullable = false, unique = true)
    private Integer CVV;

    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName", nullable = false)
    private User user;

    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
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
    public Integer getCVV() {
        return CVV;
    }
    public void setCVV(Integer CVV) {
        this.CVV = CVV;
    }
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
