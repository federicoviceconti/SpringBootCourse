package com.example.restfulwebservices.model;

public class Name {
    private String name, lastName;

    public Name(String first, String last) {
        this.name = first;
        this.lastName = last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
