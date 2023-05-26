package com.example;

import java.util.List;

public interface IService {
    void logInBoss(String email, String password, IServiceObserverBoss client) throws Exception;

    void logOutBoss(Boss boss, IServiceObserverBoss client) throws Exception;

    void logOutEmployee(Employee employee, IServiceObserver client) throws Exception;

    void logInEmployee(String email, String password, IServiceObserver client) throws Exception;

    List<EmployeeAndArrivalTime> getLoggedInEmployees(Boss boss) throws Exception;

    void addTaskOfEmployees(TaskOfEmployee task) throws Exception;

    void updateTaskOfEmployees(TaskOfEmployee task) throws Exception;

}
