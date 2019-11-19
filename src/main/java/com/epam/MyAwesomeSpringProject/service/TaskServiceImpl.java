package com.epam.MyAwesomeSpringProject.service;

import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;
import com.epam.MyAwesomeSpringProject.repository.TaskRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    public TaskServiceImpl(TaskRepoImpl taskRepo) {
        this.taskRepo = taskRepo;
    }

    private TaskRepoImpl taskRepo;

    public long create(Task task, long userId) {
        task.setUserId(userId);
        return taskRepo.create(task);
    }

    public long delete(long id) {
        return taskRepo.deleteById(id);
    }

    public Boolean setTaskComplete(long id) {
        return taskRepo.setTaskComplete(id);
    }

    public Boolean setTaskIncomplete(long id) {
        return taskRepo.setTaskIncomplete(id);
    }

    public long priorityChange(long id, Priority priority) {
        Task task = taskRepo.getById(id);
        task.setPriority(priority);
        return taskRepo.update(task);
    }

    public List<Task> getTasksByUser(long id) {
        return taskRepo.getTasksByUser(id);
    }

    public Task getById(Long id) {
        return taskRepo.getById(id);
    }

    public long update(Task object) {
        return taskRepo.update(object);
    }

    public List<Task> getAll() {
        return taskRepo.getAll();
    }
}
