package com.example;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        RepoTaskOfEmployee task = new RepoTaskOfEmployee();
        task.add(new TaskOfEmployee(1, 1, TaskStatus.SENT));
    }
}