package com.bookstore.rest.Controller;

import com.bookstore.rest.Models.Authors;
import com.bookstore.rest.Models.Books;
import com.bookstore.rest.Repo.AuthorRepository;
import com.bookstore.rest.Repo.BookRepository;
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
        Optional<Books> book = bookRepository.findByISBN(ISBN);

        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
