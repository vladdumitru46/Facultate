package com.example.objectProtocol;

import com.example.EmployeeAtOffice;

public class FindEmployeeResponse implements Response {
    private EmployeeAtOffice employeeAtOffice;

    public FindEmployeeResponse(EmployeeAtOffice employeeAtOffice) {
        this.employeeAtOffice = employeeAtOffice;
    }

    public EmployeeAtOffice getEmployeeAtOffice() {
        return employeeAtOffice;
    }
}
