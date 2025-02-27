package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart") // Remove cartId from the path
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/books/{userId}") // Use userId in the path
    public ResponseEntity<List<Book>> getBooks(@PathVariable long userId) {
        List<Book> books = shoppingCartService.getBooksInCart(userId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/subtotal/{userId}") // Use userId in the path
    public ResponseEntity<Double> getSubtotal(@PathVariable long userId) {
        double subtotal = shoppingCartService.getSubtotal(userId);
        return ResponseEntity.ok(subtotal);
    }
}