package com.oocl.springboot_exercise.Service;

import com.oocl.springboot_exercise.Common.Exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.Model.Employee;
import com.oocl.springboot_exercise.Repository.EmployeeRepository;
import com.oocl.springboot_exercise.Service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void should_add_employee_successfully() {
        // Given
        Employee employee = new Employee("oocl", 25, "male", 20045.5, true);
        Employee mockEmployee = new Employee(1,employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary(), employee.isActive());
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenReturn(mockEmployee);
        // When
        Employee saveEmployee = employeeService.addEmployee(employee);
        // Then
        assertEquals(employee.getAge(), saveEmployee.getAge());
        assertEquals(employee.getGender(), saveEmployee.getGender());
        assertEquals(employee.isActive(), saveEmployee.isActive());
        assertEquals(employee.getSalary(), saveEmployee.getSalary());
        assertNotNull(saveEmployee.getId());
    }

    @Test
    public void should_throw_exception_when_add_employee_given_age_below_18() {
        // Given
        Employee employee = new Employee("oocl", 15, "male", 20045.5, true);
        // When
        // Then
        InvalidEmployeeException invalidEmployeeException = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("员工年龄应为18-65岁", invalidEmployeeException.getMessage());
    }
}