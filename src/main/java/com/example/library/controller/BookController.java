package com.example.library.controller;

import com.example.library.entity.BookEntity;
import com.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // Constructor Injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET all books
    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    //CREATE new book
    @PostMapping
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    //UPDATE book
    @PutMapping("/{id}")
    public ResponseEntity<BookEntity> updateBook(
            @PathVariable Long id,
            @RequestBody BookEntity book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    //DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
