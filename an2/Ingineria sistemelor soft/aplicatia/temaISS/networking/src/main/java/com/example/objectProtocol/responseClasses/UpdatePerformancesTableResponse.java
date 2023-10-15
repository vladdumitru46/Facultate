package com.example.objectProtocol.responseClasses;

import com.example.dto.TaskOfEmployeeDTO;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class UpdatePerformancesTableResponse implements UpdateResponse {

    private final TaskOfEmployeeDTO task;

    public UpdatePerformancesTableResponse(TaskOfEmployeeDTO task) {
        this.task = task;
    }

    public TaskOfEmployeeDTO getTask() {
        return task;
    }
}
