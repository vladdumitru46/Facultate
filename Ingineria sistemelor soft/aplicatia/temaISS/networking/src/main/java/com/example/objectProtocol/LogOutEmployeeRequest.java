package com.example.objectProtocol;

import com.example.Employee;

public class LogOutEmployeeRequest implements Request {
    private final Employee employee;

    public LogOutEmployeeRequest(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
