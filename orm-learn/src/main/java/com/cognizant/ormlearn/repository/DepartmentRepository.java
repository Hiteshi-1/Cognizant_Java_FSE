package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Department entity.
 * Inherits standard CRUD methods from JpaRepository.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
