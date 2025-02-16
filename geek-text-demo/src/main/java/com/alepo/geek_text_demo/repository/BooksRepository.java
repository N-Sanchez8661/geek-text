package com.alepo.geek_text_demo.repository;

import com.alepo.geek_text_demo.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {

}
