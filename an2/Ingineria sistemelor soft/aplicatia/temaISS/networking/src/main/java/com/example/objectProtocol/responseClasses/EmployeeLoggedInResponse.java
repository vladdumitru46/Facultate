package com.example.objectProtocol.responseClasses;

import com.example.Employee;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class EmployeeLoggedInResponse implements UpdateResponse {
    private final Employee employee;

    public EmployeeLoggedInResponse(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
