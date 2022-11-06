package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.add(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void update(User newUser, int id) {
        userRepository.update(newUser, id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
