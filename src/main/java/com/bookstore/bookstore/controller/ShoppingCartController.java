package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.ShoppingCartItem;
import com.bookstore.bookstore.service.ShoppingCartService;
import com.bookstore.bookstore.dto.GetShoppingCartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{userId}/items")
    public ResponseEntity<List<GetShoppingCartItemResponse>> getCartItems(@PathVariable Long userId) {
        List<ShoppingCartItem> cartItems = shoppingCartService.getItemsInCart(userId);
        List<GetShoppingCartItemResponse> dtos = cartItems.stream().map(item -> {
            GetShoppingCartItemResponse dto = new GetShoppingCartItemResponse();
            dto.setCartItemId(item.getCartItemId());
            dto.setBookId(item.getBook().getBookId());
            dto.setTitle(item.getBook().getTitle());
            dto.setPrice(item.getBook().getPrice());
            dto.setGenre(item.getBook().getGenre());
            dto.setPublishedYear(item.getBook().getPublishedYear());
            dto.setISBN(item.getBook().getISBN());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{userId}/subtotal")
    public ResponseEntity<Double> getSubtotal(@PathVariable Long userId) {
        return ResponseEntity.ok(shoppingCartService.getSubtotal(userId));
    }

    @PostMapping("/{userId}/books/{bookId}")
    public ResponseEntity<Void> addBookToCart(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            shoppingCartService.addBookToCart(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Removed quantity endpoint

    @DeleteMapping("/{userId}/books/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            shoppingCartService.removeBookFromCart(userId, bookId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
