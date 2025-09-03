//package com.oocl.springboot_exercise.integration;
//
//import com.oocl.springboot_exercise.common.SimpleJsonConverter;
//import com.oocl.springboot_exercise.model.Employee;
//import com.oocl.springboot_exercise.model.Todo;
//import com.oocl.springboot_exercise.repository.EmployeeDBRepository;
//import com.oocl.springboot_exercise.repository.TodoDBRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TodoTest {
//    @Autowired
//    private MockMvc client;
//
//    @Autowired
//    private TodoDBRepository todoDBRepository;
//
//    @Test
//    public void should_return_todos_when_get_all_todos_exist() throws Exception {
//        // Case
//        List<Todo> givenTodos = todoDBRepository.getAll();
//        // When
//        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/todos"));
//
//        // Then
//        perform.andExpect(MockMvcResultMatchers.status().isOk());
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenTodos.get(0).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(givenTodos.get(0).getTitle()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].completed").value(givenTodos.get(0).getCompleted()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenTodos.get(1).getId()));
//    }
//
//    @Test
//    public void should_return_correct_todo_when_get_todo_by_id() throws Exception {
//        // Given
//        int id = 1;
//        Todo expectedTodo = todoDBRepository.getAll().stream()
//                .filter(t -> t.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("测试数据中未找到ID=" + id + "的员工"));
//
//        client.perform(MockMvcRequestBuilders.get("/api/v1/todos/{id}", id))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedTodo.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(expectedTodo.getCompleted()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(expectedTodo.getTitle()));
//    }
//
//    @Test
//    public void should_return_correct_todo_when_update_todo_by_id() throws Exception {
//        // Given
//        Integer id = 2;
//        Todo expectedTodo = new Todo(2, "学习Spring Boot", false);
//
//        client.perform(MockMvcRequestBuilders.put("/api/v1/todos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(SimpleJsonConverter.toJson(expectedTodo)))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedTodo.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(expectedTodo.getCompleted()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(expectedTodo.getTitle()));
//    }
//
//
//    @Test
//    public void should_return_no_content_when_delete_todo_by_id() throws Exception {
//        // Given
//        int id = 1;
//        client.perform(MockMvcRequestBuilders.delete("/api/v1/employees/{id}", id))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//}
