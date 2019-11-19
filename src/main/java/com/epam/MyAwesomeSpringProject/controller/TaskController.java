package com.epam.MyAwesomeSpringProject.controller;

import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;
import com.epam.MyAwesomeSpringProject.service.TaskService;
import com.epam.MyAwesomeSpringProject.subscribtion.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody String taskName) {
        taskService.createTask(authService.getUserId(), taskName);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id) {
        authService.checkAuthentication();
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}/setComplete")
    public void setTaskComplete(@PathVariable long id) {
        taskService.closeTask(id);
    }

    @PutMapping("/{id}/setIncomplete")
    public void setTaskIncomplete(@PathVariable long id) {
        taskService.openTask(id);
    }

    @PutMapping("/{id}/setPriority")
    public void priorityChange(@PathVariable long id, @RequestBody Priority priority) {
        taskService.setTaskPriorityByTaskId(id, priority);
    }

    @GetMapping
    public List<Task> getTasksByUser() {
        return taskService.findAllTasksByUser(authService.getUserId());
    }

}
