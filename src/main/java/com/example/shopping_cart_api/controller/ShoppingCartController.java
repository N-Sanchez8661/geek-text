package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.model.BookInCartDto;
import com.example.shopping_cart_api.model.OrderItem;
import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.model.User;
import com.example.shopping_cart_api.repository.AuthorRepository;
import com.example.shopping_cart_api.repository.BookRepository;
import com.example.shopping_cart_api.repository.OrderItemRepository;
import com.example.shopping_cart_api.repository.PublisherRepository;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import com.example.shopping_cart_api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{userId}/cart/subtotal")
    public ResponseEntity<BigDecimal> getSubtotal(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        ShoppingCart cart = shoppingCartRepository.findByUser(user);
        if (cart == null) {
            return ResponseEntity.notFound().build(); // Or handle as needed (e.g., create cart)
        }

        List<OrderItem> orderItems = orderItemRepository.findByShoppingCart(cart);

        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            BigDecimal price = BigDecimal.valueOf(item.getPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            subtotal = subtotal.add(price.multiply(quantity));
        }

        return ResponseEntity.ok(subtotal);
    }

    @GetMapping("/{userId}/cart/items")
    public ResponseEntity<List<BookInCartDto>> getCartItems(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        ShoppingCart cart = shoppingCartRepository.findByUser(user);
        if (cart == null) {
            return ResponseEntity.notFound().build(); // Or handle as needed (e.g., create cart)
        }

        List<OrderItem> orderItems = orderItemRepository.findByShoppingCart(cart);

        List<BookInCartDto> bookDtos = orderItems.stream()
                .map(orderItem -> {
                    Book book = orderItem.getBook();
                    if (book == null) {
                        return null; // Or throw an exception if you want to stop the process
                    }
                    BookInCartDto dto = modelMapper.map(book, BookInCartDto.class);

                    if (book.getAuthor() != null) {
                        dto.setAuthorName(book.getAuthor().getName());
                    }
                    if (book.getPublisher() != null) {
                        dto.setPublisherName(book.getPublisher().getName());
                    }

                    return dto;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bookDtos);
    }
}
