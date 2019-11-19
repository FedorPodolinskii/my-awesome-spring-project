package com.epam.MyAwesomeSpringProject.controller;

import com.epam.MyAwesomeSpringProject.entity.Priority;
import com.epam.MyAwesomeSpringProject.entity.Task;
import com.epam.MyAwesomeSpringProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long createTask(@RequestBody Task task, long userId) {
        return taskService.create(task, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public long deleteTask(@PathVariable long id) {
        return taskService.delete(id);
    }

    @PutMapping("/{id}/setComplete")
    public Boolean setTaskComplete(@PathVariable long id) {
        return taskService.setTaskComplete(id);
    }

    @PutMapping("/{id}/setIncomplete")
    public Boolean setTaskIncomplete(@PathVariable long id) {
        return taskService.setTaskIncomplete(id);
    }

    @PutMapping("/{id}/setPriority")
    public long priorityChange(@PathVariable long id, @RequestBody Priority priority) {
        return taskService.priorityChange(id, priority);
    }

    @GetMapping("/allByUser/{userId}")
    public List<Task> getTasksByUser(@PathVariable long userId) {
        return taskService.getTasksByUser(userId);
    }

    @GetMapping("/all")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping
    public long update(@RequestBody Task object) {
        return taskService.update(object);
    }

    @PostMapping("/upload")
    public void fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        //TODO fileUpload
    }


}