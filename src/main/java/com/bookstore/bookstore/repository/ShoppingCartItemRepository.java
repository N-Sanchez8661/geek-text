package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByShoppingCartCartId(Long cartId);
    List<ShoppingCartItem> findByBook_BookId(Long bookId);  // Corrected field name
}
