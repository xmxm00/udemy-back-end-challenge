package com.example.weekly_udemy;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        LocalDateTime now = LocalDateTime.now();
        book.setCreatedAt(now);
        book.setUpdatedAt(now);
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBook(Book book) {
        Book foundBook = this.findBookById(book.getId());
        if(foundBook == null) {
            throw new RuntimeException("Book not found");
        }
        foundBook.setName(book.getName());
        foundBook.setAuthor(book.getAuthor());
        foundBook.setPublisher(book.getPublisher());
        foundBook.setSummary(book.getSummary());
        foundBook.setUpdatedAt(LocalDateTime.now());
        return bookRepository.save(foundBook);
    }

    public void deleteBookById(Long id) {
        Book target = this.findBookById(id);
        if(target == null) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.delete(target);
    }
}
