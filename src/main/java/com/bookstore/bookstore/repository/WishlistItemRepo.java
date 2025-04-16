package com.bookstore.bookstore.repository;
import com.bookstore.bookstore.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepo extends JpaRepository<WishlistItem,Long>{
}