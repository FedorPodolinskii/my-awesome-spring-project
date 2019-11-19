package com.epam.MyAwesomeSpringProject.repository;

import com.epam.MyAwesomeSpringProject.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        String surname = resultSet.getString("SURNAME");
        return User.builder().id(id).name(name).surname(surname).build();
    }
}
