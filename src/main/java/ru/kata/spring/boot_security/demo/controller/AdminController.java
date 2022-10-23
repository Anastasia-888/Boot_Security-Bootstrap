package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String allUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("current_user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("allRoles", roleService.findAll());
        return "allUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Principal principal, Model model) {
        model.addAttribute("current_admin", userService.getUserByEmail(principal.getName()));

        model.addAttribute("allRoles", roleService.findAll());
        return "new";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "allRoles") List<String> allRoles) {
        List<Role> roles = new ArrayList<>();
        allRoles.forEach(role -> roles.add(roleService.getByName(role)));
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/user-update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "allRoles", required = false) List<String> allRoles) {
        List<Role> roles = new ArrayList<>();
        User currentUser = userService.getUserById(user.getId());
        if (allRoles != null) {
            allRoles.forEach(role -> {
                roles.add(roleService.getByName(role));
            });
            user.setRoles(roles);
        } else {
            user.setRoles(currentUser.getRoles());
        }
        user.setPassword(currentUser.getPassword());
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        userService.getUserById(id).setRoles(null);
        userService.delete(userService.getUserById(id));
        return "redirect:/admin";
    }
}
