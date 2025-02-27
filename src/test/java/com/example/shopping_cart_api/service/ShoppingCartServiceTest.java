package com.example.shopping_cart_api.service;

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCart cart;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        cart.setUserId(1L);
        books = new ArrayList<>();
        Book book1 = new Book();
        book1.setPrice(10.0);
        Book book2 = new Book();
        book2.setPrice(20.0);
        books.add(book1);
        books.add(book2);
        cart.setBooks(books);
    }

    @Test
    void getSubtotal_shouldReturnCorrectSubtotal() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);

        double subtotal = shoppingCartService.getSubtotal(1L);

        assertEquals(30.0, subtotal);
    }

    @Test
    void getSubtotal_shouldReturnZeroWhenCartNotFound() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(null);

        double subtotal = shoppingCartService.getSubtotal(1L);

        assertEquals(0.0, subtotal);
    }


}