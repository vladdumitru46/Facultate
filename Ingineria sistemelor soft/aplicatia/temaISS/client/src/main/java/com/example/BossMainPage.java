package com.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;


public class BossMainPage implements IServiceObserverBoss {
    Stage stage;
    Stage logInStage;
    IService serverProxy;
    Boss boss;

    Service service = new Service(new RepoBoss(), new RepoEmployee(), new RepoTask(), new RepoTaskOfEmployee(), new RepoEmployeeLogInTime());

    @FXML
    public ListView<EmployeeAndArrivalTime> listView;

    public void onSendTaskButton(ActionEvent actionEvent) {
    }

    public void onEmployeePerformancesPush(ActionEvent actionEvent) {
    }

    public void onActionsPush(ActionEvent actionEvent) {
    }

    public void onSendPush(ActionEvent actionEvent) {

    }

    public void setProxy(IService server) {
        this.serverProxy = server;
    }

    public void setService(Service service) {
    }

    @Override
    public void employeeLogIn(Employee employee) {
        Platform.runLater(() -> {
            List<EmployeeAndArrivalTime> employeeList = null;
            try {
                employeeList = serverProxy.getLoggedInEmployees(boss);
                ObservableList<EmployeeAndArrivalTime> observableList = FXCollections.observableArrayList();
                System.out.println(employeeList);
                listView.getItems().clear();
                observableList.addAll(employeeList);
                listView.getItems().setAll(observableList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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

    public void setLoggedEmployees() {
        try {
            System.out.println("sunt bine");
            List<EmployeeAndArrivalTime> employeeList = serverProxy.getLoggedInEmployees(boss);
            ObservableList<EmployeeAndArrivalTime> observableList = FXCollections.observableArrayList();
            System.out.println(employeeList);
            listView.getItems().clear();
            observableList.addAll(employeeList);
            listView.getItems().setAll(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
