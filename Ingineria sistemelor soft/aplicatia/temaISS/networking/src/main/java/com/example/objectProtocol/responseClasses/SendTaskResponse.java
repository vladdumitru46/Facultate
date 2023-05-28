package com.example.objectProtocol.responseClasses;

import com.example.TaskOfEmployee;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class SendTaskResponse implements UpdateResponse {
    private final TaskOfEmployee taskOfEmployee;

    public SendTaskResponse(TaskOfEmployee taskOfEmployee) {
        this.taskOfEmployee = taskOfEmployee;
    }

    public TaskOfEmployee getTaskOfEmployee() {
        return taskOfEmployee;
    }
}
