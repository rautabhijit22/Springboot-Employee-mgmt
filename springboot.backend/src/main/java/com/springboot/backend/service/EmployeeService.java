package com.springboot.backend.service;

import com.springboot.backend.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee e);

    List<Employee> getAllEmployees();

    Employee getEmployeebyID(long id);

    Employee updateEmployee(Employee e, long id);

    void deleteEmployee(long id);

}
