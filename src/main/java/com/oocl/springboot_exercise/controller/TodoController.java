package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.controller.dto.TodoRequest;
import com.oocl.springboot_exercise.controller.mapper.TodoMapper;
import com.oocl.springboot_exercise.model.Todo;
import com.oocl.springboot_exercise.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoMapper todoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo saveTodo(@RequestBody @Valid TodoRequest todo) {
        return todoService.saveTodo(todoMapper.toEntity(todo));
    }

    @GetMapping
    public Page<Todo> getTodos(@RequestParam int page, @RequestParam int size) {
        return todoService.getTodosByPage(page, size);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Integer id) {
        return todoService.getById(id);
    }

    @PutMapping
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Integer id) {
        todoService.deleteById(id);
    }

    @GetMapping("/page")
    public Page<Todo> getTodosByPage(@RequestParam int page, @RequestParam int size) {
        return todoService.getTodosByPage(page, size);
    }
}
