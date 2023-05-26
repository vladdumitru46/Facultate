package com.example;

import com.example.dto.TaskOfEmployeeDTO;

public interface IServiceObserverBoss {
    void employeeLogIn(Employee employee);

    void employeeLogOut(Employee employee);

    void receivedTask(TaskOfEmployee taskOfEmployee);

    void updatePerformancesTable(TaskOfEmployeeDTO taskOfEmployee);
}

