package com.example.objectProtocol;

import com.example.Employee;

public class EmployeeLoggedOutResponse implements UpdateResponse {
    private final Employee employee;

    public EmployeeLoggedOutResponse(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
