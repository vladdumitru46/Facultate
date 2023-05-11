package com.example.objectProtocol;

import com.example.TaskOfEmployee;

public class SendTaskResponse implements Response {
    private final TaskOfEmployee taskOfEmployee;

    public SendTaskResponse(TaskOfEmployee taskOfEmployee) {
        this.taskOfEmployee = taskOfEmployee;
    }

    public TaskOfEmployee getTaskOfEmployee() {
        return taskOfEmployee;
    }
}
