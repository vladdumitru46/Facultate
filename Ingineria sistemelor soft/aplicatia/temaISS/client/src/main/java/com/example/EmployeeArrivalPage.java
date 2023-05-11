package com.example;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EmployeeArrivalPage implements IServiceObserver {
    public void onPresentPush(ActionEvent actionEvent) {
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
    }

    public void setLogInStage(Stage stage) {
    }
}
