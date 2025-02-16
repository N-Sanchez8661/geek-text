package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @GetMapping("/subtotal/{user_id}")
    public ResponseEntity<Double> getSubtotal(@PathVariable Long user_id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user_id);

        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }

        if (shoppingCart.getBook() == null) {
            return ResponseEntity.ok(0.0); /
        }

        double subtotal = shoppingCart.getBook().getPrice(); 

        return ResponseEntity.ok(subtotal);
    }
}
