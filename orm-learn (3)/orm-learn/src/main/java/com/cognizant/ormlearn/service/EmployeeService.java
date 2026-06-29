package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for Employee operations.
 *
 * Day 2 HO-4 : get(), save() – ManyToOne setup
 * Day 3 HO-2 : getAllPermanentEmployees() – HQL with fetch
 * Day 3 HO-4 : getAverageSalary(), getAverageSalaryByDepartment() – HQL aggregate
 * Day 3 HO-5 : getAllEmployeesNative() – Native Query
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // ── Basic CRUD ─────────────────────────────────────────────────────────

    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start get: id={}", id);
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start save");
        employeeRepository.save(employee);
        LOGGER.info("End save");
    }

    // ── HQL Queries ────────────────────────────────────────────────────────

    /**
     * Returns all permanent employees with department and skills eagerly loaded via HQL fetch.
     */
    @Transactional
    public List<Employee> getAllPermanentEmployees() {
        LOGGER.info("Start getAllPermanentEmployees");
        return employeeRepository.getAllPermanentEmployees();
    }

    /**
     * Returns average salary across all employees.
     */
    @Transactional
    public double getAverageSalary() {
        LOGGER.info("Start getAverageSalary");
        return employeeRepository.getAverageSalary();
    }

    /**
     * Returns average salary for a specific department.
     */
    @Transactional
    public double getAverageSalaryByDepartment(int departmentId) {
        LOGGER.info("Start getAverageSalaryByDepartment: departmentId={}", departmentId);
        return employeeRepository.getAverageSalaryByDepartment(departmentId);
    }

    // ── Native Query ───────────────────────────────────────────────────────

    /**
     * Returns all employees using a native SQL query.
     */
    @Transactional
    public List<Employee> getAllEmployeesNative() {
        LOGGER.info("Start getAllEmployeesNative");
        return employeeRepository.getAllEmployeesNative();
    }
}
