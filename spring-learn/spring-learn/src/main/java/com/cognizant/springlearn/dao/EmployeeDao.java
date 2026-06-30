package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDao – Data access for employee operations.
 * Loads static data from employee.xml on startup.
 */
@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static ArrayList<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList", ArrayList.class);
        LOGGER.debug("Loaded {} employees from employee.xml", EMPLOYEE_LIST.size());
        LOGGER.info("End");
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        LOGGER.info("End");
        return EMPLOYEE_LIST;
    }

    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee found = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        LOGGER.info("End");
        return found;
    }

    public void updateEmployee(Employee updated) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(updated.getId())) {
                EMPLOYEE_LIST.set(i, updated);
                LOGGER.debug("Updated employee id={}", updated.getId());
                LOGGER.info("End");
                return;
            }
        }
        throw new EmployeeNotFoundException("Employee with id " + updated.getId() + " not found");
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        boolean removed = EMPLOYEE_LIST.removeIf(e -> e.getId() == id);
        if (!removed) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        LOGGER.debug("Deleted employee id={}", id);
        LOGGER.info("End");
    }
}
