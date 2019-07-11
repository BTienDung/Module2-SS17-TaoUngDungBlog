package com.codegym.repository;

import com.codegym.model.Blog;

import java.util.List;

public interface General<T> {
    List<T> findAll();
    void save(T t);
    T findById(Long id);
    void delete(T t);
    T findByName(String name);
}
