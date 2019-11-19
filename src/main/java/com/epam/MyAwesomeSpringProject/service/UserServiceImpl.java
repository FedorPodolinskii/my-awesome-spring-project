package com.epam.MyAwesomeSpringProject.service;

import com.epam.MyAwesomeSpringProject.entity.User;
import com.epam.MyAwesomeSpringProject.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean signUp(User user) {
            userRepo.create(user);
            return true;
    }

    public boolean signIn(User user) {
        return userRepo.findUserIdByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public User getUserById(Long id) {
        return userRepo.getById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.getAll();
    }

    public boolean subscribe(User user) {
        String subscription = DigestUtils.md5DigestAsHex(("secret".getBytes()));
        user.setSubscription(subscription);
        userRepo.update(user);
        return true;
    }

    public long create(User object) {
        return userRepo.create(object);
    }

    public User getById(Long id) {
        return userRepo.getById(id);
    }

    public long update(User object) {
        return userRepo.update(object);
    }

    public long deleteById(Long id) {
        return userRepo.deleteById(id);
    }
}
