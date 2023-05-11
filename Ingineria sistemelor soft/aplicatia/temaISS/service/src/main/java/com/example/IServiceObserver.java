package com.example;

public interface IServiceObserver {
    void employeeLogIn(Employee employee);

    void employeeLogOut(Employee employee);

    void receivedTask(TaskOfEmployee taskOfEmployee);
}
