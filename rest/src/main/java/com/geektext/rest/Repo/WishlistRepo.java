package com.geektext.rest.Repo;

import com.geektext.rest.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> {
}