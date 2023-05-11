package com.example.objectProtocol;

import com.example.Employee;

import java.util.List;

public class GetLoggedInEmployeeResponse {

    private List<Employee> list;

    public GetLoggedInEmployeeResponse(List<Employee> list) {
        this.list = list;
    }

    public List<Employee> getList() {
        return list;
    }
}
