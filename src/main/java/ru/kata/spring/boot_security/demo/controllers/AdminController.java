package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        List<User> list = userService.allUsers();
        model.addAttribute("userList", list);
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



  // @GetMapping("/{id}")
   // public String getUser(Model model, Authentication authentication) {
     //   String username = authentication.getName();
     //   model.addAttribute("user", userService.findByName(username));
       // return "user";
   // }

    @GetMapping("{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update_user";
    }

    @PostMapping("{id}")
    public String updateUser(@PathVariable("id") Long id,@ModelAttribute("user") User user) {
        userService.update(user);
        return ("redirect:/admin");
    }

    @PostMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
