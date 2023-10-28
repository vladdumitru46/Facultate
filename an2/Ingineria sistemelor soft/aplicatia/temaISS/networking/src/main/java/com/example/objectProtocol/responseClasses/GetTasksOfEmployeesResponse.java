package com.example.objectProtocol.responseClasses;

import com.example.dto.TaskOfEmployeeDTO;
import com.example.objectProtocol.interfaces.Response;

import java.util.List;

public class GetTasksOfEmployeesResponse implements Response {
    private final List<TaskOfEmployeeDTO> list;

    public GetTasksOfEmployeesResponse(List<TaskOfEmployeeDTO> list) {
        this.list = list;
    }

    public List<TaskOfEmployeeDTO> getList() {
        return list;
    }
}
