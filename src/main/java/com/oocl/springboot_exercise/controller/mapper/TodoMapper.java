package com.oocl.springboot_exercise.controller.mapper;


import com.oocl.springboot_exercise.controller.dto.TodoRequest;
import com.oocl.springboot_exercise.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todo) {
        Todo todoEntity = new Todo();
        BeanUtils.copyProperties(todo, todoEntity);
        return todoEntity;
    }
}
