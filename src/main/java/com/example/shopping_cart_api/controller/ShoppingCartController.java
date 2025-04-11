package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.ShoppingCartItem;
import com.example.shopping_cart_api.service.ShoppingCartService;
import com.example.shopping_cart_api.dto.GetShoppingCartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{userId}/items")
    public ResponseEntity<List<GetShoppingCartItemResponse>> getCartItems(@PathVariable Long userId) {
        List<ShoppingCartItem> cartItems = shoppingCartService.getItemsInCart(userId);
        List<GetShoppingCartItemResponse> dtos = cartItems.stream()
                .map(item -> {
                    GetShoppingCartItemResponse dto = new GetShoppingCartItemResponse();
                    dto.setCartItemId(item.getCartItemId());
                    dto.setbookId(item.getBook().getbookId());
                    dto.setTitle(item.getBook().getTitle());
                    dto.setQuantity(item.getQuantity());
                    dto.setPrice(item.getBook().getPrice());
                    dto.setGenre(item.getBook().getGenre());
                    dto.setPublishedYear(item.getBook().getPublishedYear());
                    dto.setISBN(item.getBook().getISBN());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{userId}/subtotal")
    public ResponseEntity<Double> getSubtotal(@PathVariable Long userId) {
        double subtotal = shoppingCartService.getSubtotal(userId);
        return ResponseEntity.ok(subtotal);
    }

    @PostMapping("/{userId}/books/{bookId}")
    public ResponseEntity<Void> addBookToCart(
            @PathVariable Long userId,
            @PathVariable Long bookId) {
        try {
            shoppingCartService.addBookToCart(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{userId}/books/{bookId}/quantity")
    public ResponseEntity<Void> addBookToCartWithQuantity(
            @PathVariable Long userId,
            @PathVariable Long bookId,
            @RequestBody Map<String, Integer> requestBody) {
        try {
            int quantity = requestBody.getOrDefault("quantity", 1);
            shoppingCartService.addBookToCart(userId, bookId, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}/books/{bookId}")
    public ResponseEntity<Void> removeBookFromCart(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            shoppingCartService.removeBookFromCart(userId, bookId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
