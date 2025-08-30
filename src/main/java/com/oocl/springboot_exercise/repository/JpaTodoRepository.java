package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTodoRepository extends JpaRepository<Todo, Integer> {
}
