package com.epam.MyAwesomeSpringProject.controller;


import com.epam.MyAwesomeSpringProject.entity.User;
import com.epam.MyAwesomeSpringProject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("userController")
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@RequestAttribute Long id) {
        return userService.getUserById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public long deleteById(@RequestAttribute Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Long> signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean subscribe(User user) {
        return userService.subscribe(user);
    }

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    /*    boolean signUp(User user);

    boolean signIn(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean subscribe(User user);

    public long create(User object);

    public User getById(Long id);

    public long update(User object);

    public long deleteById(Long id);*/
}

