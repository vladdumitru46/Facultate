package com.example;

import com.example.repository.RepoTaskOfEmployee;

public class Main {
    public static void main(String[] args) {
        RepoTaskOfEmployee task = new RepoTaskOfEmployee();
        TaskOfEmployee taskOfEmployee = task.findOne(1);
        taskOfEmployee.setTaskStatus(TaskStatus.COMPLETED);
        task.update(taskOfEmployee);
        System.out.println(task.findOne(1).getTaskStatus());
    }
}