package com.geektext.rest.Controller;

import com.geektext.rest.Models.*;
import com.geektext.rest.Repo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WishlistController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private WishlistItemRepo wishlistItemRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private ShoppingCartItemRepo shoppingCartItemRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userRepo.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/users/saved")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "User saved...";
    }

    @PutMapping("/users/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setHomeAddress(user.getHomeAddress());
            userRepo.save(updatedUser);
            return "User updated...";
        } else {
            return "User not found...";
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            userRepo.delete(optionalUser.get());

            if (userRepo.count() == 0) {
                try {
                    userRepo.resetAutoIncrement();
                } catch (Exception e) {
                    System.err.println("Failed to reset AUTO_INCREMENT: " + e.getMessage());
                }
            }

            return "User deleted with ID: " + id;
        } else {
            return "User not found...";
        }
    }

    // ---------------- Wishlist ----------------

    @GetMapping("/wishlists")
    public List<Wishlist> getWishlists() {
        return wishlistRepo.findAll();
    }

    @PostMapping("/wishlists/saved")
    public String saveWishlist(@RequestBody Wishlist wishlist) {
        wishlistRepo.save(wishlist);
        return "Wishlist saved...";
    }

    @PutMapping("/wishlists/update/{id}")
    public String updateWishlist(@PathVariable long id, @RequestBody Wishlist wishlist) {
        Optional<Wishlist> optionalWishlist = wishlistRepo.findById(id);
        if (optionalWishlist.isPresent()) {
            Wishlist updatedWishlist = optionalWishlist.get();
            updatedWishlist.setWishlistName(wishlist.getWishlistName());
            updatedWishlist.setUser(wishlist.getUser());
            wishlistRepo.save(updatedWishlist);
            return "Wishlist updated...";
        } else {
            return "Wishlist not found...";
        }
    }

    @DeleteMapping("/wishlists/delete/{id}")
    public String deleteWishlist(@PathVariable long id) {
        Optional<Wishlist> optionalWishlist = wishlistRepo.findById(id);
        if (optionalWishlist.isPresent()) {
            wishlistRepo.delete(optionalWishlist.get());
            return "Wishlist deleted with ID: " + id;
        } else {
            return "Wishlist not found...";
        }
    }

    // ---------------- Wishlist Items ----------------

    // @PostMapping("/wishlist/addBook")
    // public String addBookToWishlist(@RequestBody WishlistItem wishlistItem) {
    //     Optional<Wishlist> wishlistOpt = wishlistRepo.findById(wishlistItem.getWishlist().getWishlistId());
    //     Optional<Book> bookOpt = bookRepo.findById(wishlistItem.getBook().getBookId());

    //     if (wishlistOpt.isPresent() && bookOpt.isPresent()) {
    //         wishlistItem.setWishlist(wishlistOpt.get());
    //         wishlistItem.setBook(bookOpt.get());
    //         wishlistItemRepo.save(wishlistItem);
    //         return "Book added to wishlist successfully";
    //     }
    //     return "Wishlist or Book not found";
    // }
    @PostMapping("/wishlist/addBook")
public ResponseEntity<?> addBookToWishlist(@RequestBody WishlistBookDTO dto) {
    try {
        System.out.println("➡️ wishlistId: " + dto.wishlistId);
        System.out.println("➡️ bookId: " + dto.bookId);

        if (dto.wishlistId == null || dto.bookId == null) {
            return ResponseEntity.badRequest().body("Missing wishlistId or bookId in request.");
        }

        Optional<Wishlist> wishlistOpt = wishlistRepo.findById(dto.wishlistId);
        Optional<Book> bookOpt = bookRepo.findById(dto.bookId);

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



    @GetMapping("/wishlist/{wishlistId}/books")
    public List<WishlistItem> getWishlistBooks(@PathVariable long wishlistId) {
        return wishlistItemRepo.findAll().stream()
                .filter(item -> item.getWishlist().getWishlistId() == wishlistId)
                .toList();
    }

    @DeleteMapping("/wishlist/removeBook")
public ResponseEntity<?> removeBookFromWishlist(@RequestBody RemoveFromWishlistDTO dto) {
    try {
        System.out.println("➡️ Incoming: wishlistId=" + dto.wishlistId + ", bookId=" + dto.bookId + ", userId=" + dto.userId);

        Optional<Wishlist> wishlistOpt = wishlistRepo.findById(dto.wishlistId);
        Optional<Book> bookOpt = bookRepo.findById(dto.bookId);
        Optional<User> userOpt = userRepo.findById(dto.userId);

        if (wishlistOpt.isEmpty() || bookOpt.isEmpty() || userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Wishlist, Book, or User not found.");
        }

        Wishlist wishlist = wishlistOpt.get();
        Book book = bookOpt.get();
        User user = userOpt.get();

        List<WishlistItem> items = wishlistItemRepo.findAll();
        for (WishlistItem item : items) {
            if (item.getWishlist().getWishlistId() == dto.wishlistId &&
                item.getBook().getBookId() == dto.bookId) {

                // -- Move book to cart
                Optional<ShoppingCart> cartOpt = shoppingCartRepo.findByUser_UserId(dto.userId);
                ShoppingCart cart = cartOpt.orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setUser(user);
                    return shoppingCartRepo.save(newCart);
                });

                ShoppingCartItem cartItem = new ShoppingCartItem();
                cartItem.setCart(cart);
                cartItem.setBook(book);
                cartItem.setQuantity(1);
                shoppingCartItemRepo.save(cartItem);

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


}
