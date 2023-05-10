package com.example;

public class TaskOfEmployee extends Entity<Integer> {
    private Integer employeeId;
    private Integer taskId;
    private TaskStatus taskStatus;

    public TaskOfEmployee(Integer employeeId, Integer taskId, TaskStatus taskStatus) {
        this.employeeId = employeeId;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
