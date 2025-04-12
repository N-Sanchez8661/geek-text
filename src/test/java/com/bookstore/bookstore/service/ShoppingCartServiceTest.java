package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.model.ShoppingCartItem;
import com.bookstore.bookstore.repository.ShoppingCartItemRepository;
import com.bookstore.bookstore.repository.ShoppingCartRepository;
import com.bookstore.bookstore.repository.BookRepository;
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
        book1.setTitle("Book 1");
        book1.setGenre("Fiction");
        book1.setPublishedYear(2023);
        book1.setISBN("1234567890");
        book1.setCopiesSold(100);
        book1.setDescription("A great book");
        book1.setPublisher("Publisher A");

        Books book2 = new Books();
        book2.setBookId(2L);
        book2.setPrice(20.0);
        book2.setTitle("Book 2");
        book2.setGenre("Non-Fiction");
        book2.setPublishedYear(2022);
        book2.setISBN("0987654321");
        book2.setCopiesSold(50);
        book2.setDescription("An interesting book");
        book2.setPublisher("Publisher B");

        cartItems.add(new ShoppingCartItem(cart, book1, 1));
        cartItems.add(new ShoppingCartItem(cart, book2, 1));
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
        newBook.setTitle("Book 3");
        newBook.setGenre("Mystery");
        newBook.setPublishedYear(2024);
        newBook.setISBN("1122334455");
        newBook.setCopiesSold(20);
        newBook.setDescription("A thrilling book");
        newBook.setPublisher("Publisher C");

        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        when(bookRepository.findById(3L)).thenReturn(Optional.of(newBook));

        shoppingCartService.addBookToCart(1L, 3L);

        verify(shoppingCartItemRepository, times(1)).save(any(ShoppingCartItem.class));
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
    @Test
    void addBookToCart_defaultQuantity_isOne() {
        Books book = new Books();
        book.setBookId(20L);
        book.setTitle("Quick Add");
        book.setPrice(5.0);

        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        when(bookRepository.findById(20L)).thenReturn(Optional.of(book));

        shoppingCartService.addBookToCart(1L, 20L); // No quantity specified

        verify(shoppingCartItemRepository).save(argThat(item ->
                item.getBook().getBookId() == 20L && item.getQuantity() == 1));
    }
    @Test
    void addBookToCart_existingBook_incrementsQuantity() {
        when(shoppingCartRepository.findByUserId(1L)).thenReturn(cart);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(cartItems.get(0).getBook()));

        int originalQty = cartItems.get(0).getQuantity();

        shoppingCartService.addBookToCart(1L, 1L, 2); // Add 2 more

        assertEquals(originalQty + 2, cartItems.get(0).getQuantity());
        verify(shoppingCartItemRepository, times(1)).save(cartItems.get(0));
    }


}