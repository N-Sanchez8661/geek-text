package com.alepo.geek_text_demo.service.impl;

import com.alepo.geek_text_demo.entity.Books;
import com.alepo.geek_text_demo.repository.BooksRepository;
import com.alepo.geek_text_demo.service.BooksService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void addBooks(Books books) {
        booksRepository.save(books);
    }

    @Override
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Books getBook(Integer id) {
        Books book = booksRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid User ID " + id));

        return book;
    }

    @Override
    public void updateBook(Integer id, Books books) {
        // check whether book is in database or not
        booksRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));
        books.setId(id);
        
        booksRepository.save(books);
    }

    @Override
    public void deleteBook(Integer id) {
        // check whether book is in database or not
        Books book = booksRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));
        booksRepository.delete(book);
    }
    
}
