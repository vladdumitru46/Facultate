package com.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class BossMainPage implements IServiceObserver {
    Stage stage;
    Stage logInStage;

    @FXML
    public TextArea listView;

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
        Platform.runLater(() -> {
            System.out.println("INTRA IN PULA MEA AICI!!!!!!");
        });

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
