package com.example.shopping_cart_api.controller; // Correct package!

import com.example.shopping_cart_api.model.Book;
import com.example.shopping_cart_api.model.ShoppingCart;
import com.example.shopping_cart_api.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    public void testSubtotalEndpoint() throws Exception {
      
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Test Book");
        book.setPrice(10.0);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(1L);
        shoppingCart.setUserId(1L);
        shoppingCart.setBook(book);

        when(shoppingCartRepository.findByUserId(1L)).thenReturn(shoppingCart);

        mockMvc.perform(get("/shopping-cart/subtotal/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(10.0));
    }
} 
 
