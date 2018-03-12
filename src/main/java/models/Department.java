package models;

import java.util.Set;

public class Department {

    private int id;
    private String title;
    private Set<Employee> employees;  //set cannot contain duplicate elements, other than that treat it like an arraylist

    public Department() {}

    public Department(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


}