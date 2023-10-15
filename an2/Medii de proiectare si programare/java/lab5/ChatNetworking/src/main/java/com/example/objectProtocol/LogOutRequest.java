package com.example.objectProtocol;

import com.example.EmployeeAtOffice;

public class LogOutRequest implements Request {
    private EmployeeAtOffice employeeAtOffice;

    public LogOutRequest(EmployeeAtOffice employeeAtOffice) {
        this.employeeAtOffice = employeeAtOffice;
    }

    public EmployeeAtOffice getEmployeeAtOffice() {
        return employeeAtOffice;
    }
}
