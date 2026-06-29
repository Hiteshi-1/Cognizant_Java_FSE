package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity class mapped to the 'skill' table.
 * Hands-on 3 (Day 2) - O/R Mapping setup
 * Hands-on 6 (Day 2) - ManyToMany relationship
 */
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sk_id")
    private int id;

    @Column(name = "sk_name")
    private String name;

    /**
     * Many skills can belong to many employees.
     * mappedBy = "skillList" refers to the 'skillList' field in Employee entity.
     */
    @ManyToMany(mappedBy = "skillList")
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
        return "Skill{id=" + id + ", name='" + name + "'}";
    }
}
