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

    @GetMapping("/new")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return ("redirect:/admin");
    }


    //@GetMapping("/{id}")
    // public String getUser(Model model, Authentication authentication) {
    // String username = authentication.getName();
    // model.addAttribute("user", userService.findByEmail(username));
    // return "user";
    //}

    //  @GetMapping("/{id}")
    // public User getUser(@PathVariable("id") Long id, Model model) {
    //   return   model.addAttribute("userGet", userService.findById(id));

    // }


    @PostMapping("{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.update(user);
        return ("redirect:/admin");
    }

    @PostMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
