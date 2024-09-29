package com.example.weekly_udemy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @Deprecated
//    @GetMapping("/hello/{name}")
//    public ResponseEntity<Map> hello(@PathVariable String name) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Hello " + name + "!");
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(response);
//    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBook);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> bookList() {
        return ResponseEntity.ok(bookService.findAllBook());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> bookWithId(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok(null);
    }

}
