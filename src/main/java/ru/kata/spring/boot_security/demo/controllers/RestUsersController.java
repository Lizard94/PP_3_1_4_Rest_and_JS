package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;


@RestController
@RequestMapping("/api")
public class RestUsersController {
    private final  UserService userService;


    @Autowired
    public RestUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        List<User> usersList = userService.allUsers();
        return usersList;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
      User userGet = userService.findById(id);

      return userGet;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        userService.save(user);
        return user;

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with ID:" + id +  " is delete";

    }
    @GetMapping("/users/auth")
    public User authUser(@AuthenticationPrincipal User user) {
        return user;

    }

    @GetMapping("users/roles")
    public List<Role> findAllRoles() {
        return userService.allRoles();
    }



}
