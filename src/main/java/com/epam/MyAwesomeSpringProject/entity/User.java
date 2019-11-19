package com.epam.MyAwesomeSpringProject.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class User {
    private Long id;
    private String name;
    private String surname;
    private String subscription;
    private String role;

    public User(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
