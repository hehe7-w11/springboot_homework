package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.common.exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.repository.EmployeeRepository;
import com.oocl.springboot_exercise.service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

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
        Employee mockEmployee = new Employee(1, employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary(), employee.isActive());
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
        Employee employee = new Employee(1, "Henry", 15, "male", 20045.5, true);
        // When
        // Then
        InvalidEmployeeException invalidEmployeeException = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("员工年龄应为18-65岁", invalidEmployeeException.getMessage());
    }

    @Test
    public void should_throw_exception_when_add_employee_given_age_above_65() {
        // Given
        Employee employee = new Employee(1, "Henry", 70, "male", 20045.5, true);
        // When
        // Then
        InvalidEmployeeException invalidEmployeeException = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("员工年龄应为18-65岁", invalidEmployeeException.getMessage());
    }

    @Test
    public void should_throw_exception_when_add_employee_given_age_above_30_and_salary_below_20000() {
        // Given
        Employee employee = new Employee("oocl", 35, "male", 19852, true);
        // When
        // Then
        InvalidEmployeeException invalidEmployeeException = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("员工年龄大于等于30且薪资低于20000，不符合薪资结构", invalidEmployeeException.getMessage());
    }

    @Test
    public void should_get_employee_by_id_successfully() {
        // Given
        int id = 1;
        Employee mockEmployee = new Employee(1, "oocl", 25, "male", 20045.5, true);
        Mockito.when(employeeRepository.getById(id)).thenReturn(mockEmployee);

        // When
        Employee employee = employeeService.getEmployeeById(id);

        // Then
        assertEquals(id, employee.getId());
        assertEquals(mockEmployee.getName(), employee.getName());
        assertEquals(mockEmployee.getAge(), employee.getAge());
        assertEquals(mockEmployee.getSalary(), employee.getSalary());
    }

    @Test
    public void should_update_employee_by_id_successfully() {
        // Given
        int id = 1;
        Employee oldEmployee = new Employee(id, "Henry", 25, "male", 20045.5, true);
        Employee newEmployee = new Employee(id, "Henry", 25, "male", 23000, true);

        Mockito.when(employeeRepository.getById(id)).thenReturn(oldEmployee);
        Mockito.when(employeeRepository.updateEmployee(id, newEmployee)).thenReturn(newEmployee);

        // When
        Employee employee = employeeService.updateEmployeeById(id, newEmployee);

        // Then
        assertNotNull(employee);
        assertEquals(id, employee.getId());
        assertEquals(newEmployee.getSalary(), employee.getSalary());
        Mockito.verify(employeeRepository).getById(id);
        Mockito.verify(employeeRepository).updateEmployee(id, newEmployee);
    }

    @Test
    public void should_delete_employee_successfully() {
        // given
        Employee employee = new Employee(1, "Henry", 25, "male", 20045.5, true);
        Mockito.when(employeeRepository.getById(1)).thenReturn(employee);

        // When
        employeeService.deleteById(1);
        // then
        assertEquals(false, employee.isActive());
        Mockito.verify(employeeRepository, times(1)).getById(1);
        Mockito.verify(employeeRepository, times(1)).updateEmployee(1, employee);
    }

    @Test
    public void should_get_employee_list_successfully() {
        // given
        List<Employee> employees = List.of(new Employee(1, "Henry", 25, "male", 20045.5, true),
                new Employee(2, "Jack", 26, "male", 26000, true),
                new Employee(3, "Lucy", 27, "female", 30000, true),
                new Employee(4, "Jim", 28, "male", 50000, true));
        Mockito.when(employeeRepository.getEmployeeList()).thenReturn(employees);

        // when
        List<Employee> searchEmployees = employeeService.getEmployeeList();
        for (int i = 0; i < employees.size(); i++) {
            Employee expected = employees.get(i);
            Employee actual = searchEmployees.get(i);

            assertEquals(expected.getId(), actual.getId(), "ID不匹配");
            assertEquals(expected.getName(), actual.getName(), "姓名不匹配");
            assertEquals(expected.getAge(), actual.getAge(), "年龄不匹配");
            assertEquals(expected.getGender(), actual.getGender(), "性别不匹配");
        }
        Mockito.verify(employeeRepository, Mockito.times(1)).getEmployeeList();
    }

    @Test
    public void should_get_employee_by_gender() {
        List<Employee> employees = List.of(new Employee(1, "Henry", 25, "male", 20045.5, true),
                new Employee(2, "Jack", 26, "male", 26000, true),
                new Employee(3, "Lucy", 27, "female", 30000, true),
                new Employee(4, "Jim", 28, "male", 50000, true));
        Mockito.when(employeeRepository.getEmployeeList()).thenReturn(employees);

        List<Employee> searchEmployees = employeeService.getEmployeeByGender("female");
        List<Employee> expectedResult = List.of(new Employee(3, "Lucy", 27, "female", 30000, true));
        for (int i = 0; i < searchEmployees.size(); i++) {
            Employee expected = expectedResult.get(i);
            Employee actual = searchEmployees.get(i);
            assertEquals(expected.getId(), actual.getId(), "ID不匹配");
            assertEquals(expected.getName(), actual.getName(), "姓名不匹配");
            assertEquals(expected.getAge(), actual.getAge(), "年龄不匹配");
            assertEquals(expected.getGender(), actual.getGender(), "性别不匹配");
        }
    }
}