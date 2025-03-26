package com.alepo.geek_test.repository;

import com.alepo.geek_test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
