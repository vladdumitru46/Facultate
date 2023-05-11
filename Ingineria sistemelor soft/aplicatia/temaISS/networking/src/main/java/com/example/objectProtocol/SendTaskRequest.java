package com.example.objectProtocol;

import com.example.TaskOfEmployee;

public class SendTaskRequest implements Request {

    private final TaskOfEmployee task;

    public SendTaskRequest(TaskOfEmployee task) {
        this.task = task;
    }

    public TaskOfEmployee getTask() {
        return task;
    }
}
