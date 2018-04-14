package com.example.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> getUsers(@PathVariable("id") int id) {
        User user = userDaoService.find(id);

        if (user != null) {
            //HATEOAS
            Resource<User> resource = new Resource<>(user);
            ControllerLinkBuilder builder = linkTo(
                    methodOn(this.getClass()).getUsers()
            );

            resource.add(builder.withRel(Field.ALL_USERS));
            return resource;
        }
        throw new UserNotFoundException("Error fetch user: " + id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.add(user);
        if (savedUser != null && savedUser.getId() > 0) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId()).toUri();

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable("id") int id) {
        if (userDaoService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            throw new UserNotFoundException("User was not found: " + id);
        }
    }
}