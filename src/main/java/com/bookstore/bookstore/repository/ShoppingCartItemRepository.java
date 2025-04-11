package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    List<ShoppingCartItem> findByShoppingCartCartId(Long cartId);

    // ShoppingCartItemRepository.java
    List<ShoppingCartItem> findByBook_bookId(Long bookId); //


}