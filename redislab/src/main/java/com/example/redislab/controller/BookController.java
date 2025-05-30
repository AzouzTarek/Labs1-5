package com.example.redislab.controller;

import com.example.redislab.model.Book;
import com.example.redislab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();
        Book book = bookService.findByIdWithCacheHit(id);
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping("/cache")
    public void clearCache() {
        bookService.clearAllCaches();
    }

    @GetMapping("/batch")
    public List<Book> getBatchBooks(@RequestParam List<Long> ids) {
        return ids.stream().map(bookService::findByIdWithCacheHit).collect(Collectors.toList());
    }
}