package com.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EmployeeArrivalPage implements IServiceObserver {
    BossMainPage bossClient;

    public void onPresentPush(ActionEvent actionEvent) {
    }

    public void setProxy(IService server) {
    }

    public void setService(Service service) {
    }

    @Override
    public void employeeLogIn(Employee employee) {
        System.out.println("intrii ba in pula mea in functie??!?!?!??!!");
        Platform.runLater(() -> {
            System.out.println(employee.getName());
//            this.bossClient.listView.(employee.getName());
        });
    }

    @Override
    public void employeeLogOut(Employee employee) {

    }

    @Override
    public void receivedTask(TaskOfEmployee taskOfEmployee) {

    }

    public void setStage(Stage mainStage) {
    }

    public void setLogInStage(Stage stage) {
    }

    public void setBossClient(BossMainPage bossClient) {
        this.bossClient = bossClient;
    }
}
