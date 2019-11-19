package com.epam.MyAwesomeSpringProject.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public void catchExceprion(Exception ex){
        System.out.println("WE HAVE AN EXCEPTION! "+ ex.getMessage());
    }
}
