package com.example;

import com.example.repository.RepoEmployee;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        RepoEmployee employee = new RepoEmployee();
        Employee employee1 = employee.findOne(1);
        System.out.println(employee1.getSalary());
        employee1.setSalary(4000.00);
        employee.update(employee1);
        System.out.println(employee1.getSalary());

    }
}