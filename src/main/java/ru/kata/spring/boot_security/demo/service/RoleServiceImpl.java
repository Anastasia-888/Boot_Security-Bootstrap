package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleRepository;

    public RoleServiceImpl(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(name);
    }

}
