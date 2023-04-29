package com.example.objectProtocol;

import com.example.EmployeeAtOffice;

public class GetAllShowsRequest implements Request {
    private EmployeeAtOffice employeeAtOffice;

    public GetAllShowsRequest(EmployeeAtOffice employeeAtOffice) {
        this.employeeAtOffice = employeeAtOffice;
    }

    public EmployeeAtOffice getEmployeeAtOffice() {
        return employeeAtOffice;
    }
}
