package com.example.restfulwebservices.user;

import com.example.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoService {
    @PersistenceContext
    private EntityManager entityManager;


    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    public List<User> findAll() {
        TypedQuery<User> q = entityManager.createNamedQuery("User.findAll", User.class);
        return q.getResultList();
    }

    public User find(int id) {
        return entityManager.find(User.class, id);
    }

    public boolean deleteById(int id) {
        User user = entityManager.find(User.class, id);

        if(user != null) {
            entityManager.remove(user);
            return true;
        } else {
            return false;
        }
    }
}
