package com.example.demo.book;

import com.example.demo.book.ToFront.ToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(Long id){
        System.out.println(id);
        if (id != null){
            Book one = bookRepository.findById(id).orElseThrow(()-> new IllegalStateException("This Id doesnt exist"));
            List<Book> list = new ArrayList<>();
            list.add(one);
            return list;
        }
        List<Book> todos = bookRepository.findAll();
        return todos;
    }

    public ToFront addNewBook(Book book){
        Long id = book.getId();
        String title = book.getTitle();
        if (id != null){
            Optional<Book> bookById = bookRepository.findById(id);
            if (bookById.isPresent()){
                return new ToFront("Id already exist", false);
            }
        }
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if (bookByTitle.isPresent()) {
            return new ToFront("Book already exist", false);
        }
        bookRepository.save(book);
        return new ToFront("Added successfully", true);
    }

    public void deleteBook(Long id){
        System.out.println(id);
        if (!id.describeConstable().isPresent()){
            throw new IllegalStateException("No id is given");
        }
        boolean exists = bookRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Book with id" + id + "does not exist");
        }
        bookRepository.deleteById(id);
    }

    @Transactional
    public ToFront putBook(Book book){
        System.out.println(book);
        Long id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthor();
        Integer price = book.getPrice();
        String write = book.getWrite();
        Book book1 = bookRepository.findById(id).orElseThrow(()-> new IllegalStateException("This Book doesnt exist"));
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if (bookByTitle.isPresent() && bookByTitle.get().getId()!=id ) {
            return new ToFront("This title already exists", false);
        }
        if (title != null){book1.setTitle(title);}
        if (author != null){book1.setAuthor(author);}
        if (price != null){book1.setPrice(price);}
        if (write != null){book1.setWrite(write);}
        return new ToFront("Edited successfully", true);
    }
}
