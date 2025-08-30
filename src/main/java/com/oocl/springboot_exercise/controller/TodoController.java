package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.controller.dto.TodoRequest;
import com.oocl.springboot_exercise.controller.mapper.TodoMapper;
import com.oocl.springboot_exercise.model.Todo;
import com.oocl.springboot_exercise.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoMapper todoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo saveTodo(@RequestBody TodoRequest todo){
        return todoService.saveTodo(todoMapper.toEntity(todo));
    }

    @GetMapping
    public List<Todo> getTodos(){
        return todoService.getAllTodos();
    }
}
