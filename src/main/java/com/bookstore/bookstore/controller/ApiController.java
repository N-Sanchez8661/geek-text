package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.Authors;
import com.bookstore.bookstore.model.Books;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    private ResponseEntity<Object> getBook;

    @GetMapping(value= "/")
    public String getPage() {
        return "Welcome to the bookstore";
    }

    @GetMapping(value = "/authors")
    public List<Authors> getAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping(value = "/books")
    public List<Books> getBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{ISBN}")
    public ResponseEntity<Books> getBookByISBN(@PathVariable String ISBN){
        Optional<Books> findByIsbn = bookRepository.findByIsbn(ISBN);
        if(findByIsbn.isPresent()){
            return new ResponseEntity<>(findByIsbn.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/authors/{AuthorID}/books")
    public ResponseEntity<List<Books>> getBooksByAuthorID(@PathVariable int AuthorID){
        List<Books> books = bookRepository.findByAuthorId(AuthorID);

        if (books.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping("/authors")
    public ResponseEntity<Authors> addAuthor(@RequestBody Authors author){
        Authors newAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }

    @PostMapping("/books")
    public ResponseEntity<Books> addBook(@RequestBody Books book){
        Books newBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }


}
