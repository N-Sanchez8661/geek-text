package com.alepo.geek_test.controller;

import com.alepo.geek_test.model.Books;
import com.alepo.geek_test.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    public String addBook(@RequestBody Books book){
        bookRepository.save(book);
        return "successfully added book";
    }

    @GetMapping
    public List<Books> getBooks(){
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC , "copies"));
    }

    @GetMapping("/genre")
    public List<Books> getBooksByGenre(@RequestParam String genre){

        Books book = new Books();
        book.setGenre(genre);
        Example<Books> example = Example.of(book);

        return bookRepository.findAll(example , Sort.by(Sort.Direction.DESC , "copies"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id){
        Books book = bookRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Invalid book id " + id));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/apply-discount")
    public ResponseEntity<Void> applyDiscount(@RequestParam String publisher , @RequestParam Double discount){
        List<Books> list = bookRepository.findAll();
        for(Books b : list){
            if(b.getPublisher().equalsIgnoreCase(publisher)){
                double newPrice = b.getPrice() * discount;
                b.setPrice(newPrice);
                bookRepository.save(b);
            }
        }
        return ResponseEntity.noContent().build();
    }

}
