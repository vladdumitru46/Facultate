package com.example.objectProtocol;

import com.example.dto.TaskOfEmployeeDTO;

public class UpdatePerformancesTableResponse implements UpdateResponse {

    private final TaskOfEmployeeDTO task;

    public UpdatePerformancesTableResponse(TaskOfEmployeeDTO task) {
        this.task = task;
    }

    public TaskOfEmployeeDTO getTask() {
        return task;
    }
}
