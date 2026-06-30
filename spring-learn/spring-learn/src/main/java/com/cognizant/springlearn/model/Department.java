package com.cognizant.springlearn.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Department {

    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 1, max = 30, message = "Department name should be between 1 and 30 characters")
    private String name;

    public Department() {}

    public Integer getId()             { return id; }
    public void setId(Integer id)      { this.id = id; }

    public String getName()            { return name; }
    public void setName(String name)   { this.name = name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
