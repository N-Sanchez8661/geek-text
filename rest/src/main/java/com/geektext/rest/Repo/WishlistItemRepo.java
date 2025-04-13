package com.geektext.rest.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.geektext.rest.Models.WishlistItem;


public interface WishlistItemRepo extends JpaRepository<WishlistItem, Long> {
}
