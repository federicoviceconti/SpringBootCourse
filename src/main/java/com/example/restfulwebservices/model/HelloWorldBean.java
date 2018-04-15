package com.example.restfulwebservices.model;


public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HelloWorldBean setMessage(String message) {
        this.message = message;
        return this;
    }
}
