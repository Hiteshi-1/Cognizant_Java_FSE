package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Employee entity.
 *
 * Hands-on 2 (Day 3) - HQL: get all permanent employees with fetch
 * Hands-on 4 (Day 3) - HQL: aggregate - average salary
 * Hands-on 5 (Day 3) - Native Query: get all employees
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // ── Day 3 HQL Queries ─────────────────────────────────────────────────

    /**
     * HQL with LEFT JOIN FETCH to eagerly load department and skills in one query.
     * 'fetch' keyword populates the associated beans; without it, lazy collections
     * would throw LazyInitializationException outside the session.
     */
    @Query("SELECT e FROM Employee e " +
           "LEFT JOIN FETCH e.department d " +
           "LEFT JOIN FETCH e.skillList " +
           "WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    /**
     * HQL aggregate: average salary across all employees.
     */
    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    /**
     * HQL aggregate: average salary filtered by department id.
     * :id is a named parameter bound by @Param.
     */
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalaryByDepartment(@Param("id") int departmentId);

    /**
     * Native SQL query to get all employees.
     * nativeQuery = true tells Spring Data to treat this as raw SQL.
     */
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
