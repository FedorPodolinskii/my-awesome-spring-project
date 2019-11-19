package com.epam.MyAwesomeSpringProject.service;

import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;

import java.util.List;

public interface TaskService {

    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    List<Task> findAllTasksByUser(Long userId);
    void closeTask(Long taskId);
    void openTask(Long taskId);

    void setTaskPriorityByTaskId(Long taskId, Priority taskPriority);

}
