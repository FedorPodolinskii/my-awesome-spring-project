package com.epam.MyAwesomeSpringProject.repository;

import java.util.List;

public interface GenericRepo<T, PK> {



    long create(T object);



    T getById(PK id);



    public long update(T object);



    public long deleteById(PK id);



    public List<T> getAll();

}
