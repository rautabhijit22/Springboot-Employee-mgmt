package com.springboot.backend.service.impl;

import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepository;
import com.springboot.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    @Override
    public Employee getEmployeebyID(long id) {
        Optional<Employee> e = employeeRepository.findById(id);
        if(e.isPresent()){
            return e.get();
        }
        else{
            throw new ResourceNotFoundException("e", "ID", id);
        }
    }

    @Override
    public Employee updateEmployee(Employee e, long id) {
        //check if employee id is present in database
        Employee existEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        existEmployee.setFirstName(e.getFirstName());
        existEmployee.setLastName(e.getLastName());
        existEmployee.setEmail(e.getEmail());

        //save exist empl in DB

        employeeRepository.save(existEmployee);
        return existEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        //check if emplyeee exist in DB
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id", id));
        employeeRepository.deleteById(id);
    }
}
