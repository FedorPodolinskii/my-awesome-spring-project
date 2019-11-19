package com.epam.MyAwesomeSpringProject.repository;


import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("ID");
        Long user_id = resultSet.getLong("USER_ID");
        String name = resultSet.getString("NAME");
        String text = resultSet.getString("TEXT");
        Boolean completeness = resultSet.getBoolean("COMPLETENESS");
        Priority priority = (Priority) resultSet.getObject("PRIORITY");
        Blob file = resultSet.getBlob("file");
        return Task.builder()
                .id(id)
                .userId(user_id)
                .name(name)
                .text(text)
                .completeness(completeness)
                .priority(priority)
                .file(file)
                .build();
    }
}

