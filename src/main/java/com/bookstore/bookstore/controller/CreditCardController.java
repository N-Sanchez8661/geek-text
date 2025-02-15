package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.CreditCard;
import com.bookstore.bookstore.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardController(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<CreditCard>> getCreditCardsByUsername(@PathVariable String userName) {
        System.out.println("Finding credit cards for user: " + userName);
        List<CreditCard> creditCards = creditCardRepository.findByUser_UserName(userName);
        return ResponseEntity.ok(creditCards);
    }
}