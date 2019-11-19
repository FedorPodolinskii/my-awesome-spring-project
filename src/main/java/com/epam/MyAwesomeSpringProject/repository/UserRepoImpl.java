package com.epam.MyAwesomeSpringProject.repository;

import com.epam.MyAwesomeSpringProject.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepoImpl implements UserRepo {

    private final String SELECT_BY_ID = "select ID, NAME, SURNAME from user where id=?";
    private final String INSERT = "insert into user (id, name, surname) values (?,?,?)";
    private final String UPDATE = "update user set name=?, surname=? where id=?";
    private final String DELETE = "delete from user where id=?";
    private final String SELECT_ALL = "select * from user";

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long create(User user) {
        return jdbcTemplate.update(INSERT, user.getId(),user.getName(),user.getSurname());
    }

    public User getById(Long id) {

        return jdbcTemplate.queryForObject(SELECT_BY_ID, new UserRowMapper(), id);
    }

    public long update(User user) {
        return jdbcTemplate.update(UPDATE, user.getName(),user.getSurname(), user.getId());
    }

    public long deleteById(Long id) {
        return (long) jdbcTemplate.update(DELETE, id);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new UserRowMapper());
    }
}
