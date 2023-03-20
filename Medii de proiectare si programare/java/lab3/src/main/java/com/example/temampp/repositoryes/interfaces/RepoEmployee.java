package com.example.temampp.repositoryes.interfaces;

import com.example.temampp.domains.EmployeeAtOffice;

public interface RepoEmployee {
    boolean logIn(String email, String password);

    void logOut();

    EmployeeAtOffice findOneByEmailAndPassword(String email, String password);
}
