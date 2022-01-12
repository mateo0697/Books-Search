package com.example.demo.book;

import javax.persistence.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.lang.Integer;

@Entity
@Table
public class Book implements Comparable<Book> {

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

    @Size(min = 1, message = "Name cant be empty") //Valido que el titulo sea de 1 o mas caracteres
    private String title;

    private String author;//no tiene validaciones

    private Integer price;//Valido que solo sean numeros

    private LocalDate write;//Verifico que la fecha sea valida y que tenga formato (dd-mm-yyyy)


    public Book() {
    }

    public Book(Long id,
                String title,
                String author,
                Integer price,
                LocalDate write) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.write = write;
    }

    public Book(String title,
                String author,
                Integer price,
                LocalDate write) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getWrite() {
        return write;
    }

    public void setWrite(LocalDate write) {
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

    @Override
    public int compareTo(Book o) {
        return title.compareTo(o.getTitle());
    }
}
