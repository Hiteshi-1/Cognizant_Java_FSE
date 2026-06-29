package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity class mapped to the 'department' table.
 * Hands-on 3 (Day 2) - O/R Mapping setup
 * Hands-on 5 (Day 2) - OneToMany relationship
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private int id;

    @Column(name = "dp_name")
    private String name;

    /**
     * One department can have many employees.
     * FetchType.EAGER ensures employee list is loaded along with department.
     * mappedBy = "department" refers to the 'department' field in Employee entity.
     */
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Employee> employeeList;

    // ── Getters & Setters ──────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Employee> getEmployeeList() { return employeeList; }
    public void setEmployeeList(Set<Employee> employeeList) { this.employeeList = employeeList; }

    // ── toString ───────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
