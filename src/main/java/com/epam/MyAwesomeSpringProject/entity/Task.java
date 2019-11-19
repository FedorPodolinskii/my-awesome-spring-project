package com.epam.MyAwesomeSpringProject.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private String name;
    private String text;
    @NonNull
    private Boolean completeness;
    @NonNull
    private Priority priority;
    private Blob file;

    public Task(Long id, Long userId, String name, String text, Boolean completeness, Blob file) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.text = text;
        this.completeness = completeness;
        this.priority = Priority.LOW;
        this.file = file;

    }

    public Task(Long userId, String name, String text, Boolean completeness, Blob file) {
        this.userId = userId;
        this.name = name;
        this.text = text;
        this.completeness = completeness;
        this.priority = Priority.LOW;
        this.file = file;
    }

    public Task(Long userId, String name, String text, boolean completeness, Priority priority) {
        this.userId = userId;
        this.name = name;
        this.text = text;
        this.completeness = completeness;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(userId, task.userId) &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }
}


