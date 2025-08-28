package com.oocl.springboot_exercise.integration;

import com.oocl.springboot_exercise.common.SimpleJsonConverter;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.repository.EmployeeRepository;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        employeeRepository.getEmployeeList().clear();
        employeeRepository.save(new Employee(1, "John", 24, "male", 20202, true));
        employeeRepository.save(new Employee(2, "Jim", 27, "male", 25468, true));
        employeeRepository.save(new Employee(3, "Lucy", 26, "female", 36555, true));
        employeeRepository.save(new Employee(4, "Henry", 25, "male", 80000, true));
        employeeRepository.save(new Employee(5, "Linda", 27, "female", 70000, true));
    }

    @Test
    public void should_return_employees_when_get_all_employees_exist() throws Exception {
        // Case
        List<Employee> givenEmployees = employeeRepository.getEmployeeList();

        // When
        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees"));

        // Then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenEmployees.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].salary").value(givenEmployees.get(0).getSalary()));

        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id").value(givenEmployees.get(3).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].id").value(givenEmployees.get(4).getId()));
    }

    @Test
    public void should_return_correct_employee_when_get_employee_by_id() throws Exception {
        // Given
        int id = 1;
        Employee expectedEmployee = employeeRepository.getEmployeeList().stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("测试数据中未找到ID=" + id + "的员工"));


        client.perform(MockMvcRequestBuilders.get("/api/v1/employees/{id}", id))
                // Then：
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedEmployee.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedEmployee.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(expectedEmployee.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(expectedEmployee.getGender()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(expectedEmployee.getSalary()));
    }

    @Test
    public void should_return_correct_employee_when_update_employee_by_id() throws Exception {
        // Given
        Integer id = 1;
        Employee expectedEmployee = new Employee(1, "John", 26, "male", 25000, true);


        client.perform(MockMvcRequestBuilders.put("/api/v1/employees/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(SimpleJsonConverter.toJson(expectedEmployee)))
                // Then：
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedEmployee.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedEmployee.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(expectedEmployee.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(expectedEmployee.getGender()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(expectedEmployee.getSalary()));
    }

    @Test
    public void should_return_correct_employee_when_get_employee_by_gender() throws Exception {
        // Given
        List<Employee> expectedEmployees = employeeRepository.getEmployeeList().stream()
                .filter(emp -> emp.getGender().equals("male")).toList();


        client.perform(MockMvcRequestBuilders.get("/api/v1/employees?gender={gender}", "male"))
                // Then：
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(expectedEmployees.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(expectedEmployees.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(expectedEmployees.get(0).getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender").value(expectedEmployees.get(0).getGender()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value(expectedEmployees.get(0).getSalary()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(expectedEmployees.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(expectedEmployees.get(2).getId()));
    }

}
