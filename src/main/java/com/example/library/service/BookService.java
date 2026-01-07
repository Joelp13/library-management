package com.example.library.service;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Constructor Injection (BEST PRACTICE)
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 🔹 Get all books
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    // 🔹 Get book by ID
    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // 🔹 Add new book
    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    // 🔹 Update existing book
    public BookEntity updateBook(Long id, BookEntity book) {
        BookEntity existingBook = getBookById(id);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        return bookRepository.save(existingBook);
    }

    // 🔹 Delete book
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
