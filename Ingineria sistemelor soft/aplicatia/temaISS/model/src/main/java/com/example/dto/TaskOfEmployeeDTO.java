package com.example.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskOfEmployeeDTO implements Serializable {
    private Integer employeeId;
    private String employeeName;
    private String taskName;
    private LocalDate deadline;
    private String status;

    public TaskOfEmployeeDTO(Integer employeeId, String employeeName, String taskName, LocalDate deadline, String status) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
