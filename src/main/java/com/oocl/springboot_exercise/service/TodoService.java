package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.controller.dto.TodoRequest;
import com.oocl.springboot_exercise.model.Todo;

import java.util.List;

public interface TodoService {
    Todo saveTodo(Todo todo);

    List<Todo> getAllTodos();

    Todo updateTodo(Todo todo);

    Todo getById(Integer id);

    void deleteById(Integer id);

    List<Todo> getTodosByPage(int page, int size);
}
