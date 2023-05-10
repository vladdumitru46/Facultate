package com.example;

public class Employee extends Entity<Integer>{
    private String name;
    private String email;
    private String password;
    private Double salary;

    public Employee(String name, String email, String password, Double salary) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
