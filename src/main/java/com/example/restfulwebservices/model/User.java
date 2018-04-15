package com.example.restfulwebservices.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NamedQueries (value = {
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
})
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message = "Should contains at least {min} characters!")
    @NotNull
    private String name;
    @Past(message = "Should insert a past date")
    @NotNull
    private LocalDate birthDate;

    public User() {
    }

    public User(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
