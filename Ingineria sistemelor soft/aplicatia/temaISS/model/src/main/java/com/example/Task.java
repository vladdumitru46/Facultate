package com.example;

import java.time.LocalDateTime;

public class Task extends Entity<Integer> {
    private String name;
    private String description;
    private LocalDateTime deadline;
    private TaskStatus taskStatus;

    public Task(String name, String description, LocalDateTime deadline, TaskStatus taskStatus) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
