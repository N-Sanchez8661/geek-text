package com.bookstore.rest.Repo;

import com.bookstore.rest.Models.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Authors, Long> {
    static boolean existsById(int authorID) {
        return false;
    }
}
