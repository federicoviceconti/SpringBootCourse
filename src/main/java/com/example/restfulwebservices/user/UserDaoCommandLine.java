package com.example.restfulwebservices.user;

import com.example.restfulwebservices.model.Post;
import com.example.restfulwebservices.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDaoCommandLine implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(UserDaoCommandLine.class);

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserDaoCommandLine(UserRepository userDaoService, PostRepository postRepository) {
        this.userRepository = userDaoService;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Post postFirst = new Post("1 description");
        Post postSecond = new Post("2 description");
        Post postThird = new Post("3 description");
        Post postForth = new Post("4 description");

        List<Post> postListOne = Arrays.asList(
                postFirst, postSecond, postThird, postForth
        );

        User userOne = userRepository.save(new User("Kelly", LocalDate.of(1997, 12, 2)));
        User userTwo = userRepository.save(new User("Lelly", LocalDate.of(2001, 3, 3)));

        postFirst.setUser(userOne);
        postSecond.setUser(userTwo);
        postThird.setUser(userOne);
        postForth.setUser(userTwo);

        postRepository.saveAll(postListOne);
        logger.info("Db is now full!");
    }
}
