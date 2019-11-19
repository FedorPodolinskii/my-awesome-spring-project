package com.epam.MyAwesomeSpringProject.repository;

import com.epam.MyAwesomeSpringProject.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class UserRepoImpl implements UserRepo {

    private final String SELECT_BY_ID = "select * from user where id=?";
    private final String INSERT = "insert into user (id, name, surname) values (?,?,?)";
    private final String UPDATE = "update user set name=?, surname=? where id=?";
    private final String DELETE = "delete from user where id=?";
    private final String SELECT_ALL = "select * from user";
    static final String SELECT_ID_BY_EMAIL_AND_PASSWORD = "select user_id from users where email=? and password=?";
    static final String UPDATE_SUBSCRIPTION = "update users set subscription=? where user_id=?";


    @NonNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long create(User user) {
        return jdbcTemplate.update(INSERT, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),user.getRole());    }

    public User getById(Long id) {

        return jdbcTemplate.queryForObject(SELECT_BY_ID, new UserRowMapper(), id);
    }

    public long update(User user) {
        return jdbcTemplate.update(UPDATE, user.getFirstName(),user.getLastName(), user.getId());
    }

    public long deleteById(Long id) {
        return (long) jdbcTemplate.update(DELETE, id);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new UserRowMapper());
    }

    @Override
    public Optional<Long> findUserIdByEmailAndPassword(String email, String password) {
        Long userId = jdbcTemplate.queryForObject(SELECT_ID_BY_EMAIL_AND_PASSWORD, Long.class, email, password);
        return Optional.ofNullable(userId);
    }

    @Override
    public void setSubscriptionByUserId(Long userId, String subscription) {
        jdbcTemplate.update(UPDATE_SUBSCRIPTION, subscription, userId);
    }
}
