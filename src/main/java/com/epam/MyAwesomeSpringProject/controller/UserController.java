package com.epam.MyAwesomeSpringProject.controller;


import com.epam.MyAwesomeSpringProject.entity.User;
import com.epam.MyAwesomeSpringProject.service.UserService;
import com.epam.MyAwesomeSpringProject.service.UserServiceImpl;
import com.epam.MyAwesomeSpringProject.subscribtion.AuthService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("userController")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody User user) {
        long userId = userService.signIn(user);
        authService.setUserId(userId);
    }

    @PostMapping("/subscribe")
    public void subscribe(User user) {
        userService.subscribe(user.getId());
    }
}

