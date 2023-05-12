package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeArrivalPage {
    Service service = new Service(new RepoBoss(), new RepoEmployee(), new RepoTask(), new RepoTaskOfEmployee(), new RepoEmployeeLogInTime());

    BossMainPage bossClient;
    Employee employee;
    EmployeeMainPage employeeClient;
    IService serviceProxy;
    private Stage stage;
    private Scene scene2;
    @FXML
    TextField arrivalTimeTF;

    public void onPresentPush(ActionEvent actionEvent) throws Exception {
        String stringTime = arrivalTimeTF.getText();
        DateFormat format = new SimpleDateFormat("HH:mm");
        Date date = format.parse(stringTime);
        Time time = new Time(date.getTime());
        EmployeeAndArrivalTime employeeArrivalPage = new EmployeeAndArrivalTime(employee.getId(), 0, time.toLocalTime());
        service.addEmployeeToWork(employeeArrivalPage);
        try {
            serviceProxy.logInEmployee(employee.getEmail(), employee.getPassword(), employeeClient);
            Stage mainStage = new Stage();
            mainStage.setTitle(employee.getName());
            mainStage.setScene(scene2);
            employeeClient.setEmployeeToWork(employeeArrivalPage);
            employeeClient.setBossClient(bossClient);
            employeeClient.setStage(mainStage);
            employeeClient.setLogInStage(stage);
            employeeClient.initializeV();
            mainStage.show();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log in error");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    public void setProxy(IService server) {
        this.serviceProxy = server;
    }

    public void setService(Service service) {
    }


    public void setStage(Stage mainStage) {
        this.stage = mainStage;
    }

    public void setLogInStage(Stage stage) {
    }

    public void setBossClient(BossMainPage bossClient) {
        this.bossClient = bossClient;
    }

    public void setMainPage(EmployeeMainPage employeeClientMain) {
        this.employeeClient = employeeClientMain;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setStageMain(Scene scene3) {
        this.scene2 = scene3;
    }
}
