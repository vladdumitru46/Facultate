package com.example;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "task_of_employees")
public class TaskOfEmployee extends Entity<Integer> {
    @Column(name = "id_employee")
    private Integer employeeId;
    @Column(name = "id_task")
    private Integer taskId;
    @Column(name = "status")
    private TaskStatus taskStatus;

    public TaskOfEmployee(Integer employeeId, Integer taskId, TaskStatus taskStatus) {
        this.employeeId = employeeId;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
    }

    public TaskOfEmployee() {
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
