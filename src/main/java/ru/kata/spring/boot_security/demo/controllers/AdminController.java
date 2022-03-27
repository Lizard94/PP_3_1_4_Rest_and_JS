package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model, Authentication authentication) {
        List<User> list = userService.allUsers();
        List<Role> listRoles = userService.allRoles();
        model.addAttribute("userList", list);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("userNew", new User());
        model.addAttribute("userGet", userService.findByEmail(authentication.getName()));
        return "users";
    }


    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return ("redirect:/admin");
    }


    @PutMapping("{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.update(user);
        return ("redirect:/admin");
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
