package com.example.objectProtocol;

import com.example.Employee;

public class EmployeeLoggedInResponse implements Response {
    private final Employee employee;

    public EmployeeLoggedInResponse(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
