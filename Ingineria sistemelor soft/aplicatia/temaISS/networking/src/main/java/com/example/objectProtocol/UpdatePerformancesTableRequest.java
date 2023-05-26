package com.example.objectProtocol;

import com.example.dto.TaskOfEmployeeDTO;

public class UpdatePerformancesTableRequest implements Request {
    private final TaskOfEmployeeDTO task;

    public UpdatePerformancesTableRequest(TaskOfEmployeeDTO task) {
        this.task = task;
    }

    public TaskOfEmployeeDTO getTask() {
        return task;
    }
}
