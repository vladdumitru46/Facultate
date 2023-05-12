package com.example.objectProtocol;

import com.example.EmployeeAndArrivalTime;

import java.util.List;

public class GetLoggedInEmployeeResponse implements Response {

    private List<EmployeeAndArrivalTime> list;

    public GetLoggedInEmployeeResponse(List<EmployeeAndArrivalTime> list) {
        this.list = list;
    }

    public List<EmployeeAndArrivalTime> getList() {
        return list;
    }
}
