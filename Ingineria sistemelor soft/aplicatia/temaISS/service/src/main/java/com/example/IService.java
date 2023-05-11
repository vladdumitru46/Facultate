package com.example;

import java.util.List;

public interface IService {
    void logInBoss(String email, String password, IServiceObserver client) throws Exception;

    void logOutBoss(Boss boss, IServiceObserver client) throws Exception;

    void logOutEmployee(Employee employee, IServiceObserver client) throws Exception;

    void logInEmployee(String email, String password, IServiceObserver client) throws Exception;

    List<Employee> getLoggedInEmployees(Boss boss) throws Exception;

    void addTaskOfEmployees(TaskOfEmployee task) throws Exception;

    TaskOfEmployee updateTaskOfEmployees(TaskOfEmployee task);

}
