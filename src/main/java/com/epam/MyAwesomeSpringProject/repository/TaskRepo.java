package com.epam.MyAwesomeSpringProject.repository;

import com.epam.MyAwesomeSpringProject.entity.Task;

import java.util.List;

public interface TaskRepo extends GenericRepo<Task, Long> {

    Boolean setTaskComplete(long id);

    Boolean setTaskIncomplete(long id);

    List<Task> getTasksByUser(long userId);
}
