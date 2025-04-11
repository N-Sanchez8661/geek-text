package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.model.User;
import com.example.shopping_cart_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //GET
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return userRepository.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("Creating user: " + user);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    //PUT
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (updatedUser.getUsername() != null && !updatedUser.getUsername().equals(user.getUsername())) {
                //Check if new username already exists
                if (userRepository.findByUsername(updatedUser.getUsername()).isPresent()) {
                    return ResponseEntity.badRequest().body(null);  //Username already taken
                }
                user.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword());
            if (updatedUser.getHomeAddress() != null) user.setHomeAddress(updatedUser.getHomeAddress());
            if (updatedUser.getName() != null) user.setName(updatedUser.getName());

            userRepository.save(user);

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}