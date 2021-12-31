package com.example.demo.book;

import com.example.demo.book.ToFront.ToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")//Permito que todos los origenes usen mi api(Momentaneamente en todos ya que nadie puede acceder)
@RestController
@RequestMapping(path = "")//el path al cual llamo a la api, HTTP://localhost:8080/
public class BookController {

    private final BookService bookService;

    @Autowired //me conecta con bookservice
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping//recibe los get al path
    public List<Book> getBooks(@RequestParam(required = false) Long id){
        return bookService.getBooks(id);
    }

    @PostMapping//recibe los post al path
    public ToFront createNewBook(@Valid @RequestBody Book book, BindingResult errors){
        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors());
            return new ToFront(errors.getAllErrors().get(0).getDefaultMessage(), false);
        }else{
            System.out.println(errors.getAllErrors());
            return bookService.addNewBook(book);
        }
    }

    @DeleteMapping(path = "{bookId}")//recibe los delete al path
    public ToFront deleteBook(@PathVariable("bookId") Long id){
        return bookService.deleteBook(id);
    }

    @PutMapping//recibe los put al path
    public ToFront putBook(@Valid @RequestBody Book book, BindingResult errors) {
        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors());
            return new ToFront(errors.getAllErrors().get(0).getDefaultMessage(), false);
        }else{
            return bookService.putBook(book);
        }

    }
}
