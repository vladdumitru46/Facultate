package com.example.objectProtocol.requestClasses;

import com.example.TaskOfEmployee;
import com.example.objectProtocol.interfaces.Request;

public class SendTaskRequest implements Request {

    private final TaskOfEmployee task;

    public SendTaskRequest(TaskOfEmployee task) {
        this.task = task;
    }

    public TaskOfEmployee getTask() {
        return task;
    }
}
