package com.example.objectProtocol.requestClasses;

import com.example.Employee;
import com.example.objectProtocol.interfaces.Request;

public class LogInRequestEmployee implements Request {
    private final Employee employee;

    public LogInRequestEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}