package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {
    Todo save(Todo todo);

    List<Todo> getAll();

    Todo getById(Integer id);

    void deleteById(Integer id);

    Page<Todo> getByPage(int page, int size);
}
