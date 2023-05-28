package com.example.objectProtocol.requestClasses;

import com.example.dto.TaskOfEmployeeDTO;
import com.example.objectProtocol.interfaces.Request;

public class UpdatePerformancesTableRequest implements Request {
    private final TaskOfEmployeeDTO task;

    public UpdatePerformancesTableRequest(TaskOfEmployeeDTO task) {
        this.task = task;
    }

    public TaskOfEmployeeDTO getTask() {
        return task;
    }
}
