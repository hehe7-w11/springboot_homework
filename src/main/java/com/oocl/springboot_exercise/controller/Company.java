package com.oocl.springboot_exercise.controller;

public class Company {
    private int name;
    private Employee[] employees;

    public Company(int name, Employee[] employees) {
        this.name = name;
        this.employees = employees;
    }

    public Company() {
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}
