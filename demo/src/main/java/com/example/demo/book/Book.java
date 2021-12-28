package com.example.demo.book;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String title;
    private String author;
    private Integer price;
    private String write;

    public Book() {
    }

    public Book(Long id,
                String title,
                String author,
                Integer price,
                String write) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.write = write;
    }

    public Book(String title,
                String author,
                Integer price,
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
