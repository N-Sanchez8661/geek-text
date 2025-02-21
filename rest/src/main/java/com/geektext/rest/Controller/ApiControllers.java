package com.geektext.rest.Controller;

import com.geektext.rest.Model.User;
import com.geektext.rest.Repo.UserRepo;
import com.geektext.rest.Model.Wishlist;
import com.geektext.rest.Repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private WishlistRepo wishlistRepo;

    // User endpoints
    @GetMapping("/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
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
            updatedUser.setUserPassword(user.getUserPassword());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
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
            return "User deleted with ID: " + id;
        } else {
            return "User not found...";
        }
    }

    // Wishlist Endpoints
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
}
