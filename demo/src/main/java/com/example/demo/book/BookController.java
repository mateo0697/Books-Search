package com.example.demo.book;

import com.example.demo.book.ToFront.ToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) Long id){
        return bookService.getBooks(id);
            }

    @PostMapping
    public ToFront createNewBook(@RequestBody Book book){
        return bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long id){
        bookService.deleteBook(id);
    }

    @PutMapping
    public ToFront putBook(@RequestBody Book book){
        return bookService.putBook(book);
    }
}
