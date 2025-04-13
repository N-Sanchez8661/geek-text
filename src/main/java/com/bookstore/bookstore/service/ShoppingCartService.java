package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.model.ShoppingCartItem;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.ShoppingCartItemRepository;
import com.bookstore.bookstore.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public double getSubtotal(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            return 0.0;
        }

        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getBook().getPrice())  // Each item counts as 1 unit
                .sum();
    }

    public List<ShoppingCartItem> getItemsInCart(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        return cart == null ? List.of() : cart.getCartItems();
    }

    @Transactional
    public void addBookToCart(Long userId, Long bookId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("No shopping cart found for user ID: " + userId);
        }

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        // Always add a new entry (even if duplicate)
        ShoppingCartItem newItem = new ShoppingCartItem(cart, book);
        cart.addCartItem(newItem);
        shoppingCartItemRepository.save(newItem);
    }

    @Transactional
    public void removeBookFromCart(Long userId, Long bookId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for user: " + userId);
        }

        // Remove one instance of this book (if multiple exist)
        ShoppingCartItem itemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getBook().getBookId() == bookId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found in cart"));

        cart.removeCartItem(itemToRemove);
        shoppingCartItemRepository.delete(itemToRemove);
    }
}
