package com.epam.MyAwesomeSpringProject.entity;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "id" })
public class Task {

    private Long id;
    private Long userId;
    private String name;
    private String text;
    private Boolean completeness;
    private Priority priority;

    public Task(Long id, Long userId, String name, String text, Boolean completeness) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.text = text;
        this.completeness = completeness;
        this.priority = Priority.LOW;
    }
}


