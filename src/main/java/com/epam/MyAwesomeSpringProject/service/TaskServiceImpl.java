package com.epam.MyAwesomeSpringProject.service;

import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;
import com.epam.MyAwesomeSpringProject.exeptions.NotFoundException;
import com.epam.MyAwesomeSpringProject.entity.User;
import com.epam.MyAwesomeSpringProject.repository.TaskRepo;
import com.epam.MyAwesomeSpringProject.subscribtion.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final AuthService authService;
    private final UserService userService;

    @Override
    public void createTask(Long userId, String taskName) {
        Task task = new Task(userId, taskName," ", true, Priority.LOW);
        taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Long userId = authService.getUserId();
        User user = userService.getUserById(userId);
        taskRepo.deleteById(taskId);
    }

    @Override
    public List<Task> findAllTasksByUser(Long userId) {
        return taskRepo.findByUserId(userId);
    }

    @Override
    public void closeTask(Long taskId) {
        setTaskStatus(taskId, true);
    }

    private void setTaskStatus(Long taskId, boolean taskStatus) {
        Task task = getTaskById(taskId);
        task.setCompleteness(taskStatus);
        taskRepo.save(task);
    }

    private Task getTaskById(Long taskId) {
        return taskRepo.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task", taskId));
    }

    @Override
    public void openTask(Long taskId) {
        setTaskStatus(taskId, false);
    }

    @Override
    public void setTaskPriorityByTaskId(Long taskId, Priority taskPriority) {
        Task task = getTaskById(taskId);
        task.setPriority(taskPriority);
        taskRepo.save(task);
    }










}
