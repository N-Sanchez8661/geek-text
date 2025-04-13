package com.geektext.rest.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.geektext.rest.Models.Wishlist;


public interface WishlistRepo extends JpaRepository<Wishlist, Long> {
}
