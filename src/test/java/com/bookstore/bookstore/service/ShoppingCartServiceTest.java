package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.model.ShoppingCartItem;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.ShoppingCartItemRepository;
import com.bookstore.bookstore.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCart cart;
    private List<ShoppingCartItem> cartItems;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart(1L);
        cartItems = new ArrayList<>();

        Books book1 = new Books();
        book1.setBookId(1L);
        book1.setPrice(10.0);

        Books book2 = new Books();
        book2.setBookId(2L);
        book2.setPrice(20.0);

        cartItems.add(new ShoppingCartItem(cart, book1));
        cartItems.add(new ShoppingCartItem(cart, book2));
        cart.setCartItems(cartItems);
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

    @Test
    void addBookToCart_shouldAddBookSuccessfully() {
        Books newBook = new Books();
        newBook.setBookId(3L);
        newBook.setPrice(15.0);

        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        when(bookRepository.findById(3L)).thenReturn(Optional.of(newBook));

        shoppingCartService.addBookToCart(1L, 3L);

        verify(shoppingCartItemRepository, times(1)).save(any(ShoppingCartItem.class));
    }

    @Test
    void addBookToCart_shouldAllowDuplicateBooks() {
        Books duplicateBook = cartItems.get(0).getBook(); // Same book already in cart

        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(duplicateBook));

        shoppingCartService.addBookToCart(1L, 1L); // Add same book again

        verify(shoppingCartItemRepository, times(1)).save(any(ShoppingCartItem.class));
        assertEquals(3, cart.getCartItems().size()); // original 2 + 1 duplicate
    }

    @Test
    void removeBookFromCart_shouldRemoveBookSuccessfully() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        ShoppingCartItem itemToRemove = cartItems.get(0);

        shoppingCartService.removeBookFromCart(1L, itemToRemove.getBook().getBookId());

        verify(shoppingCartItemRepository, times(1)).delete(eq(itemToRemove));
    }

    @Test
    void addBookToCart_bookNotFound() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> shoppingCartService.addBookToCart(1L, 3L));
        verify(shoppingCartItemRepository, times(0)).save(any());
    }

    @Test
    void removeBookFromCart_bookNotFound() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);

        assertThrows(IllegalArgumentException.class, () ->
                shoppingCartService.removeBookFromCart(1L, 999L));

        verify(shoppingCartItemRepository, times(0)).delete(any());
    }

    @Test
    void addBookToCart_cartNotFound_shouldThrowError() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () ->
                shoppingCartService.addBookToCart(1L, 3L));

        verify(shoppingCartItemRepository, times(0)).save(any());
    }

    @Test
    void removeBookFromCart_cartNotFound() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () ->
                shoppingCartService.removeBookFromCart(1L, 1L));

        verify(shoppingCartItemRepository, times(0)).delete(any());
    }
}
