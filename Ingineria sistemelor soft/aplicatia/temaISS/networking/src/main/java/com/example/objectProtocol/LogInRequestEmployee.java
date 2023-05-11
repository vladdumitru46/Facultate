package com.example.objectProtocol;

import com.example.Employee;

public class LogInRequestEmployee implements Request {
    private final Employee employee;

    public LogInRequestEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
