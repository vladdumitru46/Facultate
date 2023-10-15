package com.example.repositoryes.interfaces;

import com.example.EmployeeAtOffice;

public interface RepoEmployee extends Repository<Integer, EmployeeAtOffice> {
    boolean logIn(String email, String password);

    void logOut();

    EmployeeAtOffice findOneByEmailAndPassword(String email, String password);
}
