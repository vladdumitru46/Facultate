package com.example;

import com.example.repository.RepoTaskOfEmployee;

public class Main {
    public static void main(String[] args) {
        RepoTaskOfEmployee task = new RepoTaskOfEmployee();
        System.out.println(task.findOne(1));
    }
}