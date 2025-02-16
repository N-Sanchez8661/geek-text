package com.alepo.geek_text_demo.service;

import java.util.List;

import com.alepo.geek_text_demo.entity.Books;

public interface BooksService {

    void addBooks(Books books);

    List<Books> getBooks();

    Books getBook(Integer id);

    void updateBook(Integer id , Books books);

    void deleteBook(Integer id);

}
