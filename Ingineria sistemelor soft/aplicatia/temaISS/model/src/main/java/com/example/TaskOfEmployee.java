package com.example;

public class TaskOfEmployee extends Entity<Integer> {
    private Integer employeeId;
    private Integer taskId;

    public TaskOfEmployee(Integer employeeId, Integer taskId) {
        this.employeeId = employeeId;
        this.taskId = taskId;
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
}
