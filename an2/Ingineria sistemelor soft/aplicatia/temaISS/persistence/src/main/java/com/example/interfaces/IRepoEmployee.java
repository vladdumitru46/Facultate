package com.example.interfaces;

import com.example.Employee;

public interface IRepoEmployee extends IRepository<Employee, Integer> {
    Employee findByEmailAndPassword(String email, String password);
}
