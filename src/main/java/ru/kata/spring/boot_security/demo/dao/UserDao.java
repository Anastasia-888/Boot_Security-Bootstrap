package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void add(User user);

    User getUserById(int id);

    void delete(User user);

    void update(User newUser, int id);

    User getUserByEmail(String email);
}
