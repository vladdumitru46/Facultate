package com.example.objectProtocol.responseClasses;

import com.example.Employee;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class EmployeeLoggedOutResponse implements UpdateResponse {
    private final Employee employee;

    public EmployeeLoggedOutResponse(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
