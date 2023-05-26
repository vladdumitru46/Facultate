package com.example.controller;

import com.example.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LogIn {
    Service service = new Service(new RepoBoss(), new RepoEmployee(), new RepoTask(), new RepoTaskOfEmployee(), new RepoEmployeeLogInTime());
    IService serviceProxy;
    BossMainPage bossClient;
    EmployeeArrivalPage employeeClient;
    EmployeeMainPage employeeClientMain;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField passwordTF;
    private Stage stage = new Stage();
    private Scene scene;
    private Scene scene2;
    private Scene scene3;

    public void onLogInPush(ActionEvent actionEvent) {
        Boss boss = service.getBossByEmailAndPassword(emailTF.getText(), passwordTF.getText());
        if (boss != null) {
            System.out.println("Nu e null!");
            try {
                serviceProxy.logInBoss(emailTF.getText(), passwordTF.getText(), bossClient);
                Stage mainStage = new Stage();
                mainStage.setTitle(boss.getName());
                mainStage.setScene(scene);
                bossClient.setStage(mainStage);
                bossClient.setLogInStage(stage);
                bossClient.setLoggedEmployees();
                bossClient.setProxy(serviceProxy);
                bossClient.setBoss(boss);
                mainStage.show();
                stage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Log in error");
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        } else {
            System.out.println("e null");

            Employee employee = service.getEmployeeByEmailAndPassword(emailTF.getText(), passwordTF.getText());
            if (employee != null) {
                Stage mainStage = new Stage();
                mainStage.setTitle(employee.getName());
                mainStage.setScene(scene2);
                employeeClient.setBossClient(bossClient);
                employeeClient.setStage(mainStage);
                employeeClient.setLogInStage(stage);
                employeeClient.setMainPage(employeeClientMain);
                employeeClient.setEmployee(employee);
                employeeClient.setProxy(serviceProxy);
                employeeClient.setStageMain(scene3);
                mainStage.show();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Log in error");
                alert.setHeaderText("Email or password invalid!");
                alert.show();
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setProxy(IService server) {
        this.serviceProxy = server;
    }

    public void setClient(BossMainPage mainPage) {
        this.bossClient = mainPage;
    }

    public void setParent(Parent parent) {
        this.scene = new Scene(parent);
    }

    public void setClientEmployee(EmployeeArrivalPage mainPageEmployee) {
        this.employeeClient = mainPageEmployee;
    }

    public void setParentEmployee(Parent parentEmployee) {
        this.scene2 = new Scene(parentEmployee);
    }

    public void setParentEmployeeMain(Parent parentEmployeeMain) {
        this.scene3 = new Scene(parentEmployeeMain);
    }

    public void setClientEmployeeMain(EmployeeMainPage mainPageEmployeeMain) {
        this.employeeClientMain = mainPageEmployeeMain;
    }
}