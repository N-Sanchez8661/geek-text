package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.model.OrderItem;
import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.repository.OrderItemRepository;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/items/{user_id}")
    public ResponseEntity<List<Book>> getCartItems(@PathVariable Long user_id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user_id);
        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }

        List<OrderItem> orderItems = orderItemRepository.findByShoppingCart_CartId(shoppingCart.getCartId());
        List<Book> books = new ArrayList<>();

        if (orderItems != null) {
            for(OrderItem orderItem : orderItems) {
                books.add(orderItem.getBook());
            }
        }

        return ResponseEntity.ok(books);
    }

    @GetMapping("/subtotal/{user_id}")
    public ResponseEntity<Double> getSubtotal(@PathVariable Long user_id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user_id);
        if (shoppingCart == null) {
            return ResponseEntity.notFound().build();
        }

        List<OrderItem> orderItems = orderItemRepository.findByShoppingCart_CartId(shoppingCart.getCartId());

        double subtotal = 0.0;
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                subtotal += orderItem.getBook().getPrice() * orderItem.getQuantity();
            }
        }

        return ResponseEntity.ok(subtotal);
    }
}
