package com.epam.MyAwesomeSpringProject.repository;

import com.epam.MyAwesomeSpringProject.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("taskRepository")
public class TaskRepoImpl implements TaskRepo {

/*    private Long id;
    private Long userId;
    private String name;
    private String text;
    private Boolean completeness;
    private Priority priority;*/

    private final String SELECT_BY_ID = "select ID, USER_ID, NAME, TEXT, COMPLETENESS, PRIORITY from TASK where id=?";
    private final String INSERT = "insert into TASK (ID, USER_ID, NAME, TEXT, COMPLETENESS, PRIORITY ) values (?,?,?,?,?,?)";
    private final String UPDATE = "update TASK set USER_ID=?, NAME=?, TEXT=?, COMPLETENESS=?,PRIORITY=? where id=?";
    private final String UPDATE_COMPLETENESS = "update task set completeness=? where id=?";
    private final String DELETE = "delete from TASK where id=?";
    private final String SELECT_ALL = "select * from TASK";
    private final String SELECT_ALL_BY_USER = "select * from TASK where user_id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean setTaskComplete(long id) {
        jdbcTemplate.update(UPDATE_COMPLETENESS, true, id);
        return true;
    }

    public Boolean setTaskIncomplete(long id) {
        jdbcTemplate.update(UPDATE_COMPLETENESS, false, id);
        return true;
    }


    public List<Task> getTasksByUser(long userId) {
        return new ArrayList<Task>(Arrays.<Task>asList((Task) jdbcTemplate.query(SELECT_ALL_BY_USER, new TaskRowMapper(), userId)));
    }

    public long create(Task task) {
        return jdbcTemplate.update(INSERT, task.getId(), task.getUserId(), task.getName(), task.getText(), task.getCompleteness(), task.getPriority());
    }

    public Task getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, new TaskRowMapper(), id);
    }

    public long update(Task task) {
        return jdbcTemplate.update(UPDATE, task.getUserId(), task.getName(), task.getText(), task.getCompleteness(), task.getPriority(), task.getId());
    }

    public long deleteById(Long id) {
        return (long) jdbcTemplate.update(DELETE, id);
    }

    public List<Task> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new TaskRowMapper());
    }

}
