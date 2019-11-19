package com.epam.MyAwesomeSpringProject.exeptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityNotFound, long id) {
        super(String.format("%s with id#%d not found.", entityNotFound, id));
    }

    public NotFoundException(String entityNotFound, String property, String propertyValue) {
        super(String.format("%s with %s: %s not found.", entityNotFound, property, propertyValue));
    }
}
