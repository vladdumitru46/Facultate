package com.example;

import javafx.stage.Stage;

public class EmployeeMainPage implements IServiceObserver {
    @Override
    public void employeeLogIn(Employee employee) {

    }

    @Override
    public void employeeLogOut(Employee employee) {

    }

    @Override
    public void receivedTask(TaskOfEmployee taskOfEmployee) {

    }

    public void setBossClient(BossMainPage bossClient) {
    }

    public void setStage(Stage mainStage) {
    }

    public void setLogInStage(Stage stage) {
    }

    public void setProxy(IService server) {
    }

    public void setService(Service service) {
    }

    public void setEmployeeToWork(EmployeeAndArrivalTime employeeArrivalPage) {
    }
}
