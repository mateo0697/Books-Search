package com.example.demo.book;

import com.example.demo.book.ToFront.ToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")//Permito que todos los origenes usen mi api(Momentaneamente en todos ya que nadie puede acceder)
@RestController
@RequestMapping(path = "books")//el path al cual llamo a la api, HTTP://localhost:8080/books
public class BookController {

    @Autowired //me conecta con bookservice
    BookService bookService;

    @GetMapping()//recibe los get al path
    public ResponseEntity<ToFront> getBooks(@RequestParam(required = false) Long id,
                                            @RequestParam(required = false) String title,
                                            @RequestParam(required = false) String author,
                                            @RequestParam(required = false) String price,
                                            @RequestParam(required = false) String write,
                                            @RequestParam(required = false) String sort,
                                            @RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer eachPage){
        return bookService.getBooks(id, title, author, price, write, sort,page, eachPage);
    }

    @PostMapping//recibe los post al path
    public ResponseEntity<ToFront> createNewBook(@Valid @RequestBody Book book, BindingResult errors){
        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors());
            return new ResponseEntity<ToFront> (new ToFront(errors.getAllErrors().get(0).getDefaultMessage(), false), HttpStatus.BAD_REQUEST);
        }else{
            System.out.println(errors.getAllErrors());
            return bookService.addNewBook(book);
        }
    }

    @DeleteMapping(path = "{bookId}")//recibe los delete al path
    public ResponseEntity<ToFront> deleteBook(@PathVariable("bookId") Long id){
        return bookService.deleteBook(id);
    }

    @PutMapping//recibe los put al path
    public ResponseEntity<ToFront> putBook(@Valid @RequestBody Book book, BindingResult errors) {
        if(errors.hasErrors()){
            System.out.println(errors.getAllErrors());
            return new ResponseEntity<ToFront> (new ToFront(errors.getAllErrors().get(0).getDefaultMessage(), false), HttpStatus.BAD_REQUEST);
        }else{
            return bookService.putBook(book);
        }

    }
}
