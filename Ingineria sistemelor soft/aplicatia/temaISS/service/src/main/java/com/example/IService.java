package com.example;

import java.util.List;

public interface IService {
    void logInBoss(String email, String password, IServiceObserver client);

    void logOutBoss(Boss boss, IServiceObserver client);

    void logOutEmployee(Employee employee, IServiceObserver client);

    void logInEmployee(String email, String password, IServiceObserver client);

    List<Employee> getLoggedInEmployees(Boss boss);

    void addTaskOfEmployees(TaskOfEmployee task);

    TaskOfEmployee updateTaskOfEmployees(TaskOfEmployee task);

}
