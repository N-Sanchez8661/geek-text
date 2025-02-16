package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.model.User;
import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import com.example.shopping_cart_api.repository.UserRepository;
import com.example.shopping_cart_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{userId}/cart/subtotal")
    public double getSubtotal(@PathVariable Long userId) {
        // Implementation for calculating subtotal
        User user = userRepository.findById(userId).orElse(null);
        List<ShoppingCart> cartItems = shoppingCartRepository.findByUser(user);
        double subtotal = 0;
        for (ShoppingCart item : cartItems) {
            Book book = bookRepository.findById(item.getBook().getId()).orElse(null);
            if (book != null) {
                subtotal += book.getPrice() * item.getQuantity();
            }
        }
        return subtotal;
    }

    @PostMapping("/{userId}/cart")
    public void addToCart(@PathVariable Long userId, @RequestParam Long bookId, @RequestParam int quantity) {
        // Implementation for adding to cart
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        ShoppingCart existingItem = shoppingCartRepository.findByUserAndBook(user, book);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            shoppingCartRepository.save(existingItem);
        } else {
            ShoppingCart newItem = new ShoppingCart();
            newItem.setUser(user);
            newItem.setBook(book);
            newItem.setQuantity(quantity);
            shoppingCartRepository.save(newItem);
        }
    }

    @GetMapping("/{userId}/cart/items")
    public List<Book> getCartItems(@PathVariable Long userId) {
        // Implementation for getting cart items
        User user = userRepository.findById(userId).orElse(null);
        List<ShoppingCart> cartItems = shoppingCartRepository.findByUser(user);
        List<Book> books = new java.util.ArrayList<>();
        for (ShoppingCart item : cartItems) {
            Book book = bookRepository.findById(item.getBook().getId()).orElse(null);
            books.add(book);
        }
        return books;
    }

    @DeleteMapping("/{userId}/cart")
    public void deleteFromCart(@PathVariable Long userId, @RequestParam Long bookId) {
        // Implementation for deleting from cart
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        ShoppingCart item = shoppingCartRepository.findByUserAndBook(user, book);
        shoppingCartRepository.delete(item);
    }
}
