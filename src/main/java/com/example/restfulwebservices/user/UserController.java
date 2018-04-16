package com.example.restfulwebservices.user;

import com.example.restfulwebservices.exception.PostMalformedException;
import com.example.restfulwebservices.exception.UserNotFoundException;
import com.example.restfulwebservices.common.Field;
import com.example.restfulwebservices.model.Post;
import com.example.restfulwebservices.model.User;
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
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> getUsers(@PathVariable(Field.ID) long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            //HATEOAS
            return getResources(
                    User.class,
                    user,
                    methodOn(this.getClass()).getUsers(),
                    Field.ALL_USERS
            );
        }
        throw new UserNotFoundException("Error fetch user: " + id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable(Field.ID) long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new UserNotFoundException("id-" + id);

        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public Resource<Post> savePostByUserId(@PathVariable(Field.ID) long id, @Valid @RequestBody Post post) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new UserNotFoundException("id-" + id);
        if(post != null) {
            post.setUser(user);
            postRepository.save(post);

            return getResources(
                    Post.class,
                    post,
                    methodOn(this.getClass()).getPostsByUserId(id),
                    Field.POST_BY_ID
            );
        } else {
            throw new PostMalformedException("Null post!");
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        if (savedUser.getId() > 0) {
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
    public ResponseEntity<User> deleteById(@PathVariable("id") long id) {
        userRepository.deleteById(id);

        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            throw new UserNotFoundException("User was not found: " + id);
        }
    }

    private <T> Resource<T> getResources(Class<T> resources, T added, Object invokationValue, String id) {
        Resource<T> resource = new Resource<>(added);
        ControllerLinkBuilder builder = linkTo(invokationValue);
        resource.add(builder.withRel(id));
        return resource;
    }
}