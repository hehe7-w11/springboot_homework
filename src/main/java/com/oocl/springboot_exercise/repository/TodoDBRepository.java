package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Todo getById(Integer id) {
        return jpaTodoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        jpaTodoRepository.deleteById(id);
    }

    @Override
    public Page<Todo> getByPage(int page, int size) {
        return jpaTodoRepository.findAll(PageRequest.of(page - 1, size));
    }

}
