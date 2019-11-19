package com.epam.MyAwesomeSpringProject.service;

import com.epam.MyAwesomeSpringProject.entity.User;

import com.epam.MyAwesomeSpringProject.exeptions.NotFoundException;
import com.epam.MyAwesomeSpringProject.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    static final String SECRET_HASH = md5Hash("secret");

    @Override
    public boolean signUp(User user) {
        Optional<User> userOptional = userRepo.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return false;
        } else {
            userRepo.save(user);
            return true;
        }
    }

    @Override
    public long signIn(User user) {
        return userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("User", "email", user.getEmail()))
                .getId();
    }

    @Override
    public void subscribe(Long userId) {
        User user = getUserById(userId);
        user.setSubscription(SECRET_HASH);
        userRepo.save(user);
    }

    private static String md5Hash(String s) {
        return DigestUtils.md5Hex(s);
    }

    @Override
    public boolean checkSubscription(Long userId) {
        User user = getUserById(userId);
        String subscription = user.getSubscription();
        return SECRET_HASH.equals(md5Hash(subscription));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
    }
}
