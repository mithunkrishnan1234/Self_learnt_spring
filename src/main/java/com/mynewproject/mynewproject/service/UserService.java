package com.mynewproject.mynewproject.service;

import com.mynewproject.mynewproject.models.User;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserService {
    private static List<User> users= new ArrayList<>();
   private static int usersCount=0;
    static {

        users.add(new User(++usersCount,"Adam",24));
        users.add(new User(++usersCount,"Eve",25));
        users.add(new User(++usersCount,"Jake",28));
    }
    public List<User> findAll()
    {
        return users;
    }
    public User findOne(int id) {

        Predicate<? super User> predicate = user -> user.getId()==id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    public User save(User user)
    {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
    public void deleteById(int id) {

        Predicate<? super User> predicate = user -> user.getId()==id;
        users.removeIf(predicate);
    }

}
