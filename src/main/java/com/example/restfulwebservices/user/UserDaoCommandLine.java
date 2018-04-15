package com.example.restfulwebservices.user;

import com.example.restfulwebservices.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDaoCommandLine implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(UserDaoCommandLine.class);

    private final UserDaoService userDaoService;

    @Autowired
    public UserDaoCommandLine(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @Override
    public void run(String... args) throws Exception {
        userDaoService.add(new User("Kelly", LocalDate.of(1997, 12, 2)));
        userDaoService.add(new User("Lelly", LocalDate.of(2001, 3, 3)));
        userDaoService.add(new User("Benny", LocalDate.of(1955, 2, 4)));

        logger.info("Db is now not empty!");
    }
}
