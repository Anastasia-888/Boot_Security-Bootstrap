package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

//    @Override
//    public boolean add(Role role) {
//        Role existedRole = roleRepository.findByName(role.getName());
//        if (!isNull(existedRole)) {
//            return false;
//        }
//        roleRepository.save(role);
//        return true;
//    }

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }

//    @Override
//    public void delete(Role role) {
//        roleRepository.delete(role);
//    }

}
