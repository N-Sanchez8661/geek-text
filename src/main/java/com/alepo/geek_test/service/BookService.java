package com.alepo.geek_test.service;

import com.alepo.geek_test.dto.BookDTO;
import com.alepo.geek_test.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    List<Book> getBooks();
    Book getBook(Integer id);
    void updateBook(Integer id, Book book);
    void deleteBook(Integer id);
    void updateTitle(Integer id, BookDTO bookDTO); //replace
    List<Book> getBooksByGenre(String genre);
    void applyDiscount(String publisher, Double discount);
}
