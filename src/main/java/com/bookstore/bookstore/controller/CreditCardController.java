package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.CreditCard;
import com.bookstore.bookstore.model.User;
import com.bookstore.bookstore.repository.CreditCardRepository;
import com.bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreditCardController(CreditCardRepository creditCardRepository, UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository = userRepository;
    }

    //GET
    @GetMapping("/user/{username}")
    public ResponseEntity<List<CreditCard>> getCreditCardsByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            List<CreditCard> creditCards = creditCardRepository.findByUser_UserId(user.get().getUserId());
            return ResponseEntity.ok(creditCards);
        }
        return ResponseEntity.notFound().build();
    }
    //POST
    @PostMapping("/user/{username}")
    public ResponseEntity<CreditCard> createCreditCard(@PathVariable String username, @RequestBody CreditCard creditCard) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            System.out.println("User not found: " + username);
            return ResponseEntity.notFound().build();
        }

    System.out.println("User found: " + user.get().getUserId());
    creditCard.setUser(user.get());
    creditCardRepository.save(creditCard);

    return ResponseEntity.ok().build();
    }
}