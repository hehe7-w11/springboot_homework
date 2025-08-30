package com.oocl.springboot_exercise.service.Impl;

import com.oocl.springboot_exercise.controller.dto.TodoRequest;
import com.oocl.springboot_exercise.controller.mapper.TodoMapper;
import com.oocl.springboot_exercise.model.Todo;
import com.oocl.springboot_exercise.repository.TodoDBRepository;
import com.oocl.springboot_exercise.repository.TodoRepository;
import com.oocl.springboot_exercise.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDBRepository todoRepository;

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.getAll();
    }

    @Override
    public Todo updateTodo(Todo todo) {
        if (todoRepository.getById(todo.getId()) != null) {
            return todoRepository.save(todo);
        }
        return null;
    }

    @Override
    public Todo getById(Integer id) {
        return todoRepository.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        Todo todo = todoRepository.getById(id);
        if (todo != null) {
            todoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Todo not found");
        }
    }

    @Override
    public List<Todo> getTodosByPage(int page, int size) {
        return todoRepository.getByPage(page, size);
    }
}
