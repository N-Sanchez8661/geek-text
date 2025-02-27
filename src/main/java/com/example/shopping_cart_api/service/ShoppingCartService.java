package com.example.shopping_cart_api.service;

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public double getSubtotal(long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            return 0.0; // Or throw an exception if cart not found
        }
        double subtotal = 0.0;
        if (cart.getBooks() != null) {
            for (Book book : cart.getBooks()) {
                if (book != null) {
                    subtotal += book.getPrice();
                }
            }
        }
        return subtotal;
    }

    public List<Book> getBooksInCart(long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            return List.of(); // Or throw an exception if cart not found
        }
        return cart.getBooks();
    }
}