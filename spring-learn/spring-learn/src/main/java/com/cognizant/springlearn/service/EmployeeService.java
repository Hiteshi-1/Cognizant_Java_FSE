package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        List<Employee> list = employeeDao.getAllEmployees();
        LOGGER.info("End");
        return list;
    }

    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee e = employeeDao.getEmployee(id);
        LOGGER.info("End");
        return e;
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeDao.updateEmployee(employee);
        LOGGER.info("End");
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeDao.deleteEmployee(id);
        LOGGER.info("End");
    }
}
