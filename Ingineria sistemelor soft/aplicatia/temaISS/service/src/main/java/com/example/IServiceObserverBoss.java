package com.example;

public interface IServiceObserverBoss {
    void employeeLogIn(Employee employee);

    void employeeLogOut(Employee employee);

    void receivedTask(TaskOfEmployee taskOfEmployee);
}

