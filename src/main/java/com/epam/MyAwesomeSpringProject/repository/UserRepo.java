package com.epam.MyAwesomeSpringProject.repository;


import com.epam.MyAwesomeSpringProject.entity.User;

import java.util.Optional;

public interface UserRepo extends GenericRepo<User, Long> {
    Optional<Long> findUserIdByEmailAndPassword(String email, String password);
    void setSubscriptionByUserId(Long userId, String subscription);
}
