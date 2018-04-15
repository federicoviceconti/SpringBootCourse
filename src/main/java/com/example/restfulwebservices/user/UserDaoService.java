package com.example.restfulwebservices.user;

import com.example.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {
    private List<User> users;

    public UserDaoService() {
        this.users = new ArrayList<>();
    }

    public User add(User user) {
        if(validateUser(user)) {
            user.setId(users.size() + 1);
            this.users.add(user);

            return user;
        } else {
            return null;
        }
    }

    private boolean validateUser(User user) {
        return user != null && user.getName() != null && user.getBirthDate() != null;
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public User find(int id) {
        Optional<User> user = users.stream().filter(it -> it.getId() == id).findFirst();
        return user.orElse(null);
    }

    public boolean deleteById(int id) {
        return this.users.removeIf(user -> user.getId() == id);
    }
}
