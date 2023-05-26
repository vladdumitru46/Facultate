package com.example.controller;

import com.example.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class EmployeeMainPage implements IServiceObserver {
    Service service;
    EmployeeAndArrivalTime employee;

    private TaskOfEmployee currentTaskOfEmployee;
    @FXML
    TableView<Task> tableView;
    IService serverProxy;

    private final ObservableList<Task> observableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<Task, Integer> name;
    @FXML
    TableColumn<Task, Integer> description;
    @FXML
    TableColumn<Task, Date> deadline;
    private Stage stage;
    @FXML
    private TabPane mainPage;
    @FXML
    private Tab taskTab;
    @FXML
    private Tab resolveTaskTab;
    @FXML
    private Label taskDescriptionLabel;

    @FXML
    private Label taskTitleLabel;


    public void initializeV() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        List<TaskOfEmployee> list = service.getTasksForEmployee(employee.getEmployeeId());
        for (var task : list) {
            Task task1 = service.findTask(task.getTaskId());
            observableList.add(task1);
        }
        tableView.setItems(observableList);
    }

    @Override
    public void employeeLogIn(Employee employee) {

    }

    @Override
    public void employeeLogOut(Employee employee) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();

    }

    @Override
    public void receivedTask(TaskOfEmployee taskOfEmployee) {
        Platform.runLater(() -> {
            Task task = service.findTask(taskOfEmployee.getTaskId());
            observableList.add(task);
            tableView.setItems(observableList);
            tableView.refresh();
        });
    }

    public void setBossClient(BossMainPage bossClient) {
    }

    public void setStage(Stage mainStage) {
        this.stage = mainStage;
    }

    public void setLogInStage(Stage stage) {
    }

    public void setProxy(IService server) {
        this.serverProxy = server;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setEmployeeToWork(EmployeeAndArrivalTime employeeArrivalPage) {
        this.employee = employeeArrivalPage;
    }

    public void onClosePush(ActionEvent actionEvent) {
        try {
            Employee employee1 = service.findEmployeeById(employee.getEmployeeId());
            EmployeeAndArrivalTime employeeAndArrivalTime = service.getEmployeeAndArrivalTimeByEmployeeId(employee1.getId());
            System.out.println(employee1.getName());
            serverProxy.logOutEmployee(employee1, this);
            service.deleteEmployeeFromWork(employeeAndArrivalTime.getId());
            stage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    public void onStartTaskPush(ActionEvent actionEvent) {
        Task task = tableView.getSelectionModel().getSelectedItem();
        if (task != null) {
            taskTitleLabel.setText(task.getName());
            taskDescriptionLabel.setText(task.getDescription());
            mainPage.getSelectionModel().select(resolveTaskTab);
            TaskOfEmployee taskOfEmployee = service.getTaskOfEmployeeByEmployeeIdAndTaskId(employee.getEmployeeId(), task.getId());
            taskOfEmployee.setTaskStatus(TaskStatus.InPROGRESS);
            service.updateTaskOfEmployees(taskOfEmployee);
            currentTaskOfEmployee = taskOfEmployee;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have to select a task!\n");
            alert.show();
        }
    }

    public void onSubmitTaskPush(ActionEvent actionEvent) {
        Task task = service.findTask(currentTaskOfEmployee.getTaskId());
        if (task.getDeadline().isAfter(LocalDate.now())) {
            currentTaskOfEmployee.setTaskStatus(TaskStatus.COMPLETED);
        } else {
            currentTaskOfEmployee.setTaskStatus(TaskStatus.LATE);
        }
        service.updateTaskOfEmployees(currentTaskOfEmployee);
    }
}

