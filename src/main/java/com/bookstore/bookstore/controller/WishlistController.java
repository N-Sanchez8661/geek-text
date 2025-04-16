package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.*;
import com.bookstore.bookstore.repository.*;
import com.bookstore.bookstore.dto.RemoveFromWishlistDTO;
import com.bookstore.bookstore.dto.WishlistBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private WishlistItemRepo wishlistItemRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private ShoppingCartRepository shoppingCartRepo;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepo;

    // ---------------- Wishlist ----------------

    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepo.findAll();
        return ResponseEntity.ok(wishlists);
    }

    @PostMapping("/saved")
    public ResponseEntity<String> saveWishlist(@RequestBody Wishlist wishlist) {
        wishlistRepo.save(wishlist);
        return ResponseEntity.ok("Wishlist saved...");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateWishlist(@PathVariable long id, @RequestBody Wishlist wishlist) {
        Optional<Wishlist> optionalWishlist = wishlistRepo.findById(id);
        if (optionalWishlist.isPresent()) {
            Wishlist updatedWishlist = optionalWishlist.get();
            updatedWishlist.setWishlistName(wishlist.getWishlistName());
            updatedWishlist.setUser(wishlist.getUser());
            wishlistRepo.save(updatedWishlist);
            return ResponseEntity.ok("Wishlist updated...");
        }
        return ResponseEntity.status(404).body("Wishlist not found...");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWishlist(@PathVariable long id) {
        Optional<Wishlist> optionalWishlist = wishlistRepo.findById(id);
        if (optionalWishlist.isPresent()) {
            wishlistRepo.delete(optionalWishlist.get());
            return ResponseEntity.ok("Wishlist deleted with ID: " + id);
        }
        return ResponseEntity.status(404).body("Wishlist not found...");
    }

    // ---------------- Wishlist Items ----------------

    @PostMapping("/addBook")
    public ResponseEntity<?> addBookToWishlist(@RequestBody WishlistBookDTO dto) {
        try {
            Optional<Wishlist> wishlistOpt = wishlistRepo.findById(dto.getWishlistId());
            Optional<Books> bookOpt = bookRepo.findById(dto.getBookId());

            if (wishlistOpt.isPresent() && bookOpt.isPresent()) {
                WishlistItem wishlistItem = new WishlistItem();
                wishlistItem.setWishlist(wishlistOpt.get());
                wishlistItem.setBook(bookOpt.get());
                wishlistItemRepo.save(wishlistItem);
                return ResponseEntity.ok("Book added to wishlist successfully");
            } else {
                return ResponseEntity.status(404).body("Wishlist or Book not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/removeBook")
    public ResponseEntity<?> removeBookFromWishlist(@RequestBody RemoveFromWishlistDTO dto) {
        try {
            Optional<Wishlist> wishlistOpt = wishlistRepo.findById(dto.getWishlistId());
            Optional<Books> bookOpt = bookRepo.findById(dto.getBookId());
            Optional<User> userOpt = userRepo.findById(dto.getUserId());

            if (wishlistOpt.isEmpty() || bookOpt.isEmpty() || userOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Wishlist, Book, or User not found.");
            }

            Wishlist wishlist = wishlistOpt.get();
            Books book = bookOpt.get();
            User user = userOpt.get();

            List<WishlistItem> items = wishlistItemRepo.findAll();
            for (WishlistItem item : items) {
                if (item.getWishlist().getWishlistId() == dto.getWishlistId() &&
                        item.getBook().getBookId() == dto.getBookId()) {

                    // Move book to cart
                    ShoppingCart cart = shoppingCartRepo.findByUserId(dto.getUserId());
                    if (cart == null) {
                        cart = new ShoppingCart();
                        cart.setUser(user);
                        cart = shoppingCartRepo.save(cart);
                    }

                    ShoppingCartItem cartItem = new ShoppingCartItem();
                    cartItem.setShoppingCart(cart);
                    cartItem.setBook(book);
                    shoppingCartItemRepo.save(cartItem); // ✅ save directly

                    wishlistItemRepo.delete(item);

                    return ResponseEntity.ok("Book moved to shopping cart and removed from wishlist");
                }
            }

            return ResponseEntity.status(404).body("Book not found in wishlist");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{wishlistId}/books")
    public List<WishlistItem> getWishlistBooks(@PathVariable long wishlistId) {
        return wishlistItemRepo.findAll().stream()
                .filter(item -> item.getWishlist().getWishlistId() == wishlistId)
                .toList();
    }
}
