package com.alepo.geek_test.service.impl;

import com.alepo.geek_test.dto.BookDTO;
import com.alepo.geek_test.entity.Book;
import com.alepo.geek_test.repository.BookRepository;
import com.alepo.geek_test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC , "copies"));
    }
    @Override
    public List<Book> getBooksByGenre(String genre){

        Book book = new Book();
        book.setGenre(genre);
        Example<Book> example = Example.of(book);

        return bookRepository.findAll(example , Sort.by(Sort.Direction.DESC , "copies"));
    }

    @Override
    public void applyDiscount(String publisher, Double discount) {

        List<Book> list = bookRepository.findAll();

        for(Book b : list){
            if(b.getPublisher().equalsIgnoreCase(publisher)){
                double newPrice = b.getPrice() * discount;
                b.setPrice(newPrice);
                bookRepository.save(b);
            }
        }

    }

    @Override
    public Book getBook(Integer id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));

        return book;
    }

    @Override
    public void updateBook(Integer id, Book book) {
        //check whether the book is in database or not
        bookRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));

        book.setBookId(id);

        bookRepository.save(book);

    }

    @Override
    public void deleteBook(Integer id) {
        //check whether the book is in database or not
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));

        bookRepository.delete(book);

    }

    @Override
    public void updateTitle(Integer id, BookDTO bookDTO) {
        //check whether the book is in database or not
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));

        book.setTitle(bookDTO.getTitle());

        bookRepository.save(book);
    }

}
