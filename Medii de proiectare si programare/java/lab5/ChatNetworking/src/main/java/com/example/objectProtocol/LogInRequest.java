package com.example.objectProtocol;

import com.example.EmployeeAtOffice;

public class LogInRequest implements Request {
    private final EmployeeAtOffice employeeAtOffice;

    public LogInRequest(EmployeeAtOffice employeeAtOffice) {
        this.employeeAtOffice = employeeAtOffice;
    }

    public EmployeeAtOffice getEmployeeAtOffice() {
        return employeeAtOffice;
    }
}
