package com.springboot.backend.controller;

import com.springboot.backend.model.Employee;
import com.springboot.backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //build create employee REST API

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee e){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(e), HttpStatus.CREATED);
    }

    //build GET all employee REST API

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //build GET employee by ID REST API

    @GetMapping("{id}")
    //https://localhost:8080/api/employees/1
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long eId){
        return new ResponseEntity<Employee>(employeeService.getEmployeebyID(eId), HttpStatus.OK);

    }

    //build update employee REST API
    //https://localhost:8080/api/employees/18
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }
    //build DELETE employee REST API
    //https://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);
         return new ResponseEntity<String>("Employee deleted successfully.", HttpStatus.OK);
    }


}
