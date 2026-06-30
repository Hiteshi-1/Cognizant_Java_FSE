package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DepartmentController – REST endpoint for Department resource.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /** GET /departments – returns all departments */
    @GetMapping
    public List<Department> getAllDepartments() {
        LOGGER.info("Start");
        List<Department> departments = departmentService.getAllDepartments();
        LOGGER.info("End");
        return departments;
    }
}
