package com.cognizant.springlearn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * Employee model bean with Bean Validation annotations (Session 3).
 */
public class Employee {

    @NotNull(message = "Employee id should not be null")
    private Integer id;

    @NotNull(message = "Employee name should not be null")
    @NotBlank(message = "Employee name should not be blank")
    @Size(min = 1, max = 30, message = "Employee name should be between 1 and 30 characters")
    private String name;

    @NotNull(message = "Salary should not be null")
    @Min(value = 0, message = "Salary should be zero or above")
    private Double salary;

    @NotNull(message = "Permanent flag should not be null")
    private Boolean permanent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String dateOfBirth;

    @Valid
    private Department department;

    @Valid
    private List<Skill> skills;

    public Employee() {}

    public Integer getId()                        { return id; }
    public void setId(Integer id)                 { this.id = id; }

    public String getName()                       { return name; }
    public void setName(String name)              { this.name = name; }

    public Double getSalary()                     { return salary; }
    public void setSalary(Double salary)          { this.salary = salary; }

    public Boolean getPermanent()                 { return permanent; }
    public void setPermanent(Boolean permanent)   { this.permanent = permanent; }

    public String getDateOfBirth()                { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth){ this.dateOfBirth = dateOfBirth; }

    public Department getDepartment()             { return department; }
    public void setDepartment(Department dept)    { this.department = dept; }

    public List<Skill> getSkills()                { return skills; }
    public void setSkills(List<Skill> skills)     { this.skills = skills; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary
                + ", permanent=" + permanent + ", dob='" + dateOfBirth + "'}";
    }
}
