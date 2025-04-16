package com.bookstore.bookstore.repository;


import com.bookstore.bookstore.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist,Long>{
        }