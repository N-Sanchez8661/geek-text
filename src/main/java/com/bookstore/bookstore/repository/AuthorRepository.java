package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Authors, Long> {
    static boolean existsById(int authorID) {
        return false;
    }
}
