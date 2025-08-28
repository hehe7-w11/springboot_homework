package com.oocl.springboot_exercise.model;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Double salary;
    private Boolean active = true;

    public Employee(int id, String name, int age, String gender, double salary, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = active;
    }

    public Employee(String name, int age, String gender, double salary, boolean active) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = active;
    }

    public Employee(){}

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
