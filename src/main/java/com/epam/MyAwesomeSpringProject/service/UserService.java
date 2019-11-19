package com.epam.MyAwesomeSpringProject.service;
import com.epam.MyAwesomeSpringProject.entity.User;

import java.util.List;

public interface UserService {

    boolean signUp(User user);

    boolean signIn(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean subscribe(User user);

    public long create(User object);

    public User getById(Long id);

    public long update(User object);

    public long deleteById(Long id);
}
