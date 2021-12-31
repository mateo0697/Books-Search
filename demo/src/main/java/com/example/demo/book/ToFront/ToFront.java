package com.example.demo.book.ToFront;

public class ToFront {
    private String message;
    private Boolean state;
    public ToFront(String message, Boolean state) {
        this.message = message;
        this.state = state;
    }

    public ToFront() {
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
