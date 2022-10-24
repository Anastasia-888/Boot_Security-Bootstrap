package ru.kata.spring.boot_security.demo.service.interfaces;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role getByName(String name);

}
