package com.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;


public class BossMainPage implements IServiceObserverBoss {
    Stage stage;
    Stage logInStage;
    IService serverProxy;
    Boss boss;

    Service service = new Service(new RepoBoss(), new RepoEmployee(), new RepoTask(), new RepoTaskOfEmployee(), new RepoEmployeeLogInTime());

    @FXML
    public ListView<String> listView;

    @FXML
    TextField titleTF;

    @FXML
    TextArea descriptionTF;

    @FXML
    DatePicker deadlineTF;

    public void onSendTaskButton(ActionEvent actionEvent) {
    }

    public void onEmployeePerformancesPush(ActionEvent actionEvent) {
    }

    public void onActionsPush(ActionEvent actionEvent) {
    }

    public void onSendPush(ActionEvent actionEvent) throws Exception {
        Task task = new Task(titleTF.getText(), descriptionTF.getText(), deadlineTF.getValue());
        service.addTask(task);
        List<Task> list = service.getAllTasks();
        task = list.get(list.size() - 1);
        System.out.println(task.getId() + " IDDDD");
        String employee = listView.getSelectionModel().getSelectedItems().toString();
        Integer id = Character.getNumericValue(employee.charAt(1));
        TaskOfEmployee taskOfEmployee = new TaskOfEmployee(id, task.getId(), TaskStatus.SENT);
        serverProxy.addTaskOfEmployees(taskOfEmployee);
    }

    public void setProxy(IService server) {
        this.serverProxy = server;
    }

    public void setService(Service service) {
    }

    @Override
    public void employeeLogIn(Employee employee) {
        Platform.runLater(() -> {
            try {
                List<EmployeeAndArrivalTime> employeeList = serverProxy.getLoggedInEmployees(boss);
                ObservableList<String> observableList = FXCollections.observableArrayList();
                for (var em : employeeList) {
                    String string = convertEmployeeToString(em);
                    observableList.add(string);
                }
                listView.getItems().clear();
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

    public String convertEmployeeToString(EmployeeAndArrivalTime employeeAndArrivalTime) {
        String stringOfEmployee = "";
        Employee employee = service.findEmployeeById(employeeAndArrivalTime.getEmployeeId());
        stringOfEmployee += employee.getId() + " " + employee.getName() + " arrived at: " + employeeAndArrivalTime.getLogInTime();
        return stringOfEmployee;
    }

    public void setLoggedEmployees() {
        try {
            System.out.println("sunt bine");
            List<EmployeeAndArrivalTime> employeeList = serverProxy.getLoggedInEmployees(boss);
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (var em : employeeList) {
                String string = convertEmployeeToString(em);
                observableList.add(string);
            }
            listView.getItems().clear();
            listView.getItems().setAll(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public void onClosePush(ActionEvent actionEvent) {
        service.logOutBoss(boss, this);
        System.exit(0);
        stage.close();
    }
}
