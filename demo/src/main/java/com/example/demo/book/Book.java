package com.example.demo.book;

import com.example.demo.book.Validations.IsNumber;
import com.example.demo.book.Validations.VerifyDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.lang.Integer;

@Entity
@Table
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;

    @Size(min = 1, message = "Title cant be empty") //Valido que el titulo sea de 1 o mas caracteres
    private String title;

    private String author;//no tiene validaciones

    @IsNumber
    private String price;//Valido que solo sean numeros

    @VerifyDate
    private String write;//Verifico que la fecha sea valida y que tenga formato (dd-mm-yyyy)

    public Book() {
    }

    public Book(Long id,
                String title,
                String author,
                String price,
                String write) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.write = write;
    }

    public Book(String title,
                String author,
                String price,
                String write) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.write = write;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", write=" + write +
                '}';
    }
}
