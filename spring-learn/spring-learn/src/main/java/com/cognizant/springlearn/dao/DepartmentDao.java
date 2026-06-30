package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * DepartmentDao – Loads department list from employee.xml.
 */
@Repository
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private static ArrayList<Department> DEPARTMENT_LIST;

    public DepartmentDao() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList", ArrayList.class);
        LOGGER.debug("Loaded {} departments from employee.xml", DEPARTMENT_LIST.size());
        LOGGER.info("End");
    }

    public List<Department> getAllDepartments() {
        LOGGER.info("Start");
        LOGGER.info("End");
        return DEPARTMENT_LIST;
    }
}
