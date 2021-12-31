package com.example.demo.book.ToFront;

import com.example.demo.book.Book;

import java.util.List;

public class ToFront {
    private String message;
    private Boolean state;
    private List<Book> books;

    public ToFront(String message, Boolean state) {
        this.message = message;
        this.state = state;
    }

    public ToFront(String message, Boolean state, List<Book> books) {
        this.message = message;
        this.state = state;
        this.books = books;
    }

    public ToFront() {
    }

    public ToFront(List<Book> books) {
        this.books = books;
    }



    public ToFront(String message) {
        this.message = message;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
