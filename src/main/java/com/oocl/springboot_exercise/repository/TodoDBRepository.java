package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDBRepository implements TodoRepository {

    @Autowired
    JpaTodoRepository jpaTodoRepository;

    @Override
    public Todo save(Todo todo) {
        return jpaTodoRepository.save(todo);
    }

    @Override
    public List<Todo> getAll() {
        return jpaTodoRepository.findAll();
    }

}
