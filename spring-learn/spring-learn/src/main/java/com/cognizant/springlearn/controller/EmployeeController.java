package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EmployeeController – REST endpoints for Employee resource.
 * Base URL "/employees" per RESTful naming convention.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /** GET /employees – returns all employees */
    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("End");
        return employees;
    }

    /** GET /employees/{id} – returns a specific employee */
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee employee = employeeService.getEmployee(id);
        LOGGER.info("End");
        return employee;
    }

    /** PUT /employees – update an employee (validated payload) */
    @PutMapping
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.updateEmployee(employee);
        LOGGER.info("End");
    }

    /** DELETE /employees/{id} – delete a specific employee */
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.deleteEmployee(id);
        LOGGER.info("End");
    }
}
