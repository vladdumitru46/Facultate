package com.example;

import java.time.LocalTime;

public class EmployeeAndArrivalTime extends Entity<Integer> {
    private Integer employeeId;
    private Integer taskId;
    private LocalTime logInTime;

    public EmployeeAndArrivalTime(Integer employeeId, Integer taskStatus, LocalTime logInTime) {
        this.employeeId = employeeId;
        this.taskId = taskStatus;
        this.logInTime = logInTime;
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

    public LocalTime getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(LocalTime logInTime) {
        this.logInTime = logInTime;
    }

    @Override
    public String toString() {
        return "EmployeeAndArrivalTime{" +
                "employeeId=" + employeeId +
                ", taskId=" + taskId +
                ", logInTime=" + logInTime +
                '}';
    }
}
