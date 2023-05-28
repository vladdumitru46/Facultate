package com.example.controller;

import com.example.*;
import com.example.dto.TaskOfEmployeeDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;


public class BossMainPage implements IServiceObserverBoss {
    Stage stage;
    Stage logInStage;
    IService serverProxy;
    Boss boss;

    Service service;
    @FXML
    public ListView<String> listView;

    @FXML
    TextField titleTF;

    @FXML
    TextArea descriptionTF;

    @FXML
    DatePicker deadlineTF;
    @FXML
    private TabPane mainPage;
    @FXML
    private Tab mainTab;
    @FXML
    private Tab sendTaskTab;
    @FXML
    private Tab performancesTab;
    @FXML
    private Tab raiseTab;


    @FXML
    TableView<TaskOfEmployeeDTO> performancesView;
    @FXML
    TableColumn<TaskOfEmployeeDTO, String> nameColumn;
    @FXML
    TableColumn<TaskOfEmployeeDTO, String> taskColumn;
    @FXML
    TableColumn<TaskOfEmployeeDTO, LocalDate> deadlineColumn;
    @FXML
    TableColumn<TaskOfEmployeeDTO, String> statusColumn;

    @FXML
    private Label employeeNameLabel;

    @FXML
    private Label curentSalaryLabel;

    @FXML
    private TextField newSalaryTF;

    private Employee currentEmployee;


    @FXML

    public void onSendTaskButton(ActionEvent actionEvent) {
        sendTaskTab.setDisable(false);
        mainPage.getSelectionModel().select(sendTaskTab);
    }

    public void onEmployeePerformancesPush(ActionEvent actionEvent) {
        mainPage.getSelectionModel().select(performancesTab);
    }

    public void onActionsPush(ActionEvent actionEvent) {
    }

    public void onSendPush(ActionEvent actionEvent) throws Exception {
        Task task = new Task(titleTF.getText(), descriptionTF.getText(), deadlineTF.getValue());
        service.addTask(task);
        List<Task> list = service.getAllTasks();
        task = list.get(list.size() - 1);
        String employee = listView.getSelectionModel().getSelectedItems().toString();
        Integer id = Character.getNumericValue(employee.charAt(1));
        TaskOfEmployee taskOfEmployee = new TaskOfEmployee(id, task.getId(), TaskStatus.SENT);
        serverProxy.addTaskOfEmployees(taskOfEmployee);
        setEmployeePerformancesTable();
        mainPage.getSelectionModel().select(mainTab);
        sendTaskTab.setDisable(true);
    }

    public void setProxy(IService server) {
        this.serverProxy = server;
    }

    public void setService(Service service) {
        this.service = service;
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
                listView.refresh();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void employeeLogOut(Employee employee) {
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
                listView.refresh();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void updatePerformancesTable(TaskOfEmployeeDTO taskOfEmployee) {
        Platform.runLater(this::setEmployeePerformancesTable);
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

    public void setEmployeePerformancesTable() {
        try {
            raiseTab.setDisable(true);
            sendTaskTab.setDisable(true);
            List<TaskOfEmployeeDTO> list = service.getTasksOfEmployeesDTO(boss);
            ObservableList<TaskOfEmployeeDTO> observableList = FXCollections.observableArrayList();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            taskColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
            deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            observableList.setAll(list);
            performancesView.setItems(observableList);
            statusColumn.setCellFactory(column -> new TableCell<TaskOfEmployeeDTO, String>() {
                @Override
                protected void updateItem(String status, boolean empty) {
                    super.updateItem(status, empty);
                    if (status != null) {
                        if (status.equals(String.valueOf(TaskStatus.InPROGRESS))) {
                            setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        if (status.equals(String.valueOf(TaskStatus.LATE))) {
                            setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                        }
                        if (status.equals(String.valueOf(TaskStatus.COMPLETED))) {
                            setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                        }
                        setTextFill(Color.BLACK);
                        setText(status);
                    }
                }
            });
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

    public void onRaisePush(ActionEvent actionEvent) {
        raiseTab.setDisable(false);
        TaskOfEmployeeDTO task = performancesView.getSelectionModel().getSelectedItem();
        if (task != null) {
            Employee employee = service.findEmployeeById(task.getEmployeeId());
            currentEmployee = employee;
            employeeNameLabel.setText(employee.getName());
            curentSalaryLabel.setText(String.valueOf(employee.getSalary()));
            mainPage.getSelectionModel().select(raiseTab);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have to select a task!\n");
            alert.show();
        }
    }

    public void onRaiseSalaryPush(ActionEvent actionEvent) {
        Double salary = Double.parseDouble(newSalaryTF.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(currentEmployee.getName());
        alert.show();
        currentEmployee.setSalary(salary);
        service.updateEmployee(currentEmployee);
        mainPage.getSelectionModel().select(performancesTab);
        raiseTab.setDisable(true);
    }

    public void onFirePush(ActionEvent actionEvent) throws Exception {
        TaskOfEmployeeDTO task = performancesView.getSelectionModel().getSelectedItem();
        Employee employee = service.findEmployeeById(task.getEmployeeId());
        serverProxy.logOutEmployee(employee);
        service.deleteTaskOfEmployeeByEmployeeId(employee.getId());
        service.deleteEmployee(employee.getId());
    }
}





