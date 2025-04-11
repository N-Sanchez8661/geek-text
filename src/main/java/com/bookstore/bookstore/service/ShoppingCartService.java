package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.repository.ShoppingCartRepository;
import com.bookstore.bookstore.model.ShoppingCartItem;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.ShoppingCartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }

    public List<ShoppingCartItem> getItemsInCart(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            return List.of();
        }
        return cart.getCartItems();
    }

    public void addBookToCart(Long userId, Long bookId) {
        addBookToCart(userId, bookId, 1);
    }

    @Transactional
    public void addBookToCart(Long userId, Long bookId, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");

        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("No shopping cart found for user ID: " + userId);
        }

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        Optional<ShoppingCartItem> existingItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getBook().getBookId() == bookId)
                .findFirst();

        if (existingItemOpt.isPresent()) {
            ShoppingCartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            shoppingCartItemRepository.save(existingItem);
        } else {
            ShoppingCartItem newItem = new ShoppingCartItem(cart, book, quantity);
            cart.addCartItem(newItem);
            shoppingCartItemRepository.save(newItem);
        }
    }

    @Transactional
    public void removeBookFromCart(Long userId, Long bookId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for user: " + userId);
        }

        ShoppingCartItem itemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getBook().getBookId() == bookId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found in cart"));

        cart.removeCartItem(itemToRemove);
        shoppingCartItemRepository.delete(itemToRemove);
    }
}
