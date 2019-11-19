package com.epam.MyAwesomeSpringProject.service;
import com.epam.MyAwesomeSpringProject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean signUp(User user);
    long signIn(User user);
    void subscribe(Long userId);
    boolean checkSubscription(Long userId);
    User getUserById(Long userId);
}