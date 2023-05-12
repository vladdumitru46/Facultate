package com.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;


public class EmployeeMainPage implements IServiceObserver {
    Service service = new Service(new RepoBoss(), new RepoEmployee(), new RepoTask(), new RepoTaskOfEmployee(), new RepoEmployeeLogInTime());

    EmployeeAndArrivalTime employee;
    @FXML
    TableView<Task> tableView;

    private ObservableList<Task> observableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<Task, Integer> name;
    @FXML
    TableColumn<Task, Integer> description;
    @FXML
    TableColumn<Task, Date> deadline;

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
    }

    public void setLogInStage(Stage stage) {
    }

    public void setProxy(IService server) {
    }

    public void setService(Service service) {
    }

    public void setEmployeeToWork(EmployeeAndArrivalTime employeeArrivalPage) {
        this.employee = employeeArrivalPage;
    }
}
