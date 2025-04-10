package com.bookstore.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    public String addBook(@RequestBody Books book) {
        bookRepository.save(book);
        return "successfully added book";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Books book = bookRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid book id " + id));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Books> getBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "copiesSold"));
    }

    @GetMapping("/genre")
    public List<Books> getBooksByGenre(@RequestParam String genre) {

        Books book = new Books();
        book.setGenre(genre);
        Example<Books> example = Example.of(book);

        return bookRepository.findAll(example, Sort.by(Sort.Direction.DESC, "copiesSold"));
    }

    @PatchMapping("/apply-discount")
    public ResponseEntity<Void> applyDiscount(@RequestParam String publisher, @RequestParam Double discount) {
        List<Books> list = bookRepository.findAll();
        for (Books b : list) {
            if (b.getPublisher().equalsIgnoreCase(publisher)) {
                double newPrice = b.getPrice() * discount;
                b.setPrice(newPrice);
                bookRepository.save(b);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rating")
    public List<Books> getBooksByRating(@RequestParam Integer rating) {
        
        List<Books> list = new ArrayList<>();

        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "copiesSold"));

    }

}
