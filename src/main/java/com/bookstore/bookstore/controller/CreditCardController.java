package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.CreditCard;
import com.bookstore.bookstore.repository.CreditCardRepository;
import com.bookstore.bookstore.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {

    private CreditCardRepository creditCardRepository;
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CreditCard>> getCreditCardsByUsername(@PathVariable String username) {
        List<CreditCard> creditCards = creditCardRepository.findByUserUserName(username);
        return ResponseEntity.ok(creditCards);
    }
}