//package com.oocl.springboot_exercise.integration;
//
//import com.oocl.springboot_exercise.common.SimpleJsonConverter;
//import com.oocl.springboot_exercise.model.Employee;
//import com.oocl.springboot_exercise.repository.EmployeeDBRepository;
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
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class EmployeeTest {
//
//    @Autowired
//    private MockMvc client;
//
//    @Autowired
//    private EmployeeDBRepository employeeMemoryRepository;
//
//
//    @Test
//    public void should_return_employees_when_get_all_employees_exist() throws Exception {
//        // Case
//        List<Employee> givenEmployees = employeeMemoryRepository.getAll();
//
//        // When
//        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees"));
//
//        // Then
//        perform.andExpect(MockMvcResultMatchers.status().isOk());
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenEmployees.get(0).getName()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id").value(givenEmployees.get(3).getId()));
//    }
//
//    @Test
//    public void should_return_correct_employee_when_get_employee_by_id() throws Exception {
//        // Given
//        int id = 2;
//        Employee expectedEmployee = employeeMemoryRepository.getAll().stream()
//                .filter(emp -> emp.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("测试数据中未找到ID=" + id + "的员工"));
//
//        client.perform(MockMvcRequestBuilders.get("/api/v1/employees/{id}", id))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedEmployee.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedEmployee.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(expectedEmployee.getAge()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(expectedEmployee.getGender()));
//    }
//
//    @Test
//    public void should_return_correct_employee_when_update_employee_by_id() throws Exception {
//        // Given
//        Integer id = 2;
//        Employee expectedEmployee = new Employee(2, "John", 26, "male", 25000, true);
//
//
//        client.perform(MockMvcRequestBuilders.put("/api/v1/employees")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(SimpleJsonConverter.toJson(expectedEmployee)))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedEmployee.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedEmployee.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(expectedEmployee.getAge()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(expectedEmployee.getGender()));
//    }
//
//    @Test
//    public void should_return_correct_employee_when_get_employee_by_gender() throws Exception {
//        // Given
//        List<Employee> expectedEmployees = employeeMemoryRepository.getAll().stream()
//                .filter(emp -> emp.getGender().equals("male")).toList();
//
//
//        client.perform(MockMvcRequestBuilders.get("/api/v1/employees?gender={gender}", "male"))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(expectedEmployees.get(0).getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(expectedEmployees.get(0).getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(expectedEmployees.get(0).getAge()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender").value(expectedEmployees.get(0).getGender()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(expectedEmployees.get(1).getId()));
//    }
//
//    @Test
//    public void should_avtive_false_when_delete_employee_by_id() throws Exception {
//        // Given
//        int id = 1;
//        client.perform(MockMvcRequestBuilders.delete("/api/v1/employees/{id}", id))
//                // Then：
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//        assertEquals(false, employeeMemoryRepository.getById(id).isActive());
//    }
//}
