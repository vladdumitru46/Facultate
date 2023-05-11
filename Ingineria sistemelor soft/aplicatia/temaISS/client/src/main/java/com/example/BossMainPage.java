package com.example;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class BossMainPage implements IServiceObserver {
    Stage stage;
    Stage logInStage;

    public void onSendTaskButton(ActionEvent actionEvent) {
    }

    public void onEmployeePerformancesPush(ActionEvent actionEvent) {
    }

    public void onActionsPush(ActionEvent actionEvent) {
    }

    public void onSendPush(ActionEvent actionEvent) {

    }

    public void setProxy(IService server) {
    }

    public void setService(Service service) {
    }

    @Override
    public void employeeLogIn(Employee employee) {

    }

    @Override
    public void employeeLogOut(Employee employee) {

    }

    @Override
    public void receivedTask(TaskOfEmployee taskOfEmployee) {

    }

    public void setStage(Stage mainStage) {
        this.stage = mainStage;
    }

    public void setLogInStage(Stage stage) {
        this.logInStage = stage;
    }
}
