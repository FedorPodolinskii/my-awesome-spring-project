package com.epam.MyAwesomeSpringProject.service;

import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;

import java.util.List;

public interface TaskService {

    long create(Task task, long userId);

    long delete(long id);

    Boolean setTaskComplete(long id);

    Boolean setTaskIncomplete(long id);

    long priorityChange(long id, Priority priority);

    List<Task> getTasksByUser(long userId);

    Task getById(Long id);

    long update(Task object);

    List<Task> getAll();

}
