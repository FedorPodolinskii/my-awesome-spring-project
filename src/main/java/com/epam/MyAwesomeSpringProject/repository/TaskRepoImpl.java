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
static final String INSERT = "insert into tasks (user_id, task_name, status, task_priority) values(?,?,?,?)";
    static final String DELETE = "delete from tasks where task_id = ?";
    static final String SELECT_ALL_BY_USER = "select * from tasks where user_id = ?";
    static final String UPDATE_COMPLETENESS = "update users set status=? where task_id=?";
    static final String SET_TASK_PRIORITY = "update users set taskPriority=? where task_id=?";
    static final String GET_TASK_COUNT_BY_USER_ID = "select task_id, count(task_id) from tasks GROUP BY task_id";
    static final String PUT_FILE = "insert into tasks (file) values (?) task_id=?";
    private final String SELECT_BY_ID = "select ID, USER_ID, NAME, TEXT, COMPLETENESS, PRIORITY from TASK where id=?";
    private final String UPDATE = "update TASK set USER_ID=?, NAME=?, TEXT=?, COMPLETENESS=?,PRIORITY=? where id=?";
    private final String SELECT_ALL = "select * from TASK";


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
        return jdbcTemplate.update(DELETE, id);
    }

    public List<Task> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new TaskRowMapper());
    }

}
