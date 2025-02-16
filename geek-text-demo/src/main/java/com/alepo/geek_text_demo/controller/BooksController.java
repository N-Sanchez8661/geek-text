package com.alepo.geek_text_demo.controller;

import com.alepo.geek_text_demo.entity.Books;
import com.alepo.geek_text_demo.repository.BooksRepository;
import com.alepo.geek_text_demo.service.BooksService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @PostMapping("/add")
    public String addBooks(@RequestBody Books books){
        booksService.addBooks(books);
        return "success add books";
    }

    @GetMapping
    public List<Books> getBooks(){
        return booksService.getBooks();
    }

    @GetMapping("/get")
    public Books getBook(@RequestParam Integer id){
        return booksService.getBook(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Integer id , @RequestBody Books book){
        booksService.updateBook(id , book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id){
        booksService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }



}
