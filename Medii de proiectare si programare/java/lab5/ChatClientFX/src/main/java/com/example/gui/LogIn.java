package com.example.gui;


import com.example.EmployeeAtOffice;
import com.example.IService;
import com.example.Service;
import com.example.repositoryes.ArtistRepo;
import com.example.repositoryes.BuyerRepo;
import com.example.repositoryes.EmployeeRepo;
import com.example.repositoryes.ShowRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Properties;

public class LogIn {

    Service service = new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties()));
    IService serviceProxy;

    MainPagePane client;
    @FXML
    TextField emailTF;
    @FXML
    TextField passwordTF;

    Stage stage = new Stage();
    private Parent mainPageParent;
    private Scene scene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setProxy(IService serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public void setClient(MainPagePane client) {
        this.client = client;
    }

    public void setParent(Parent mainPageParent) {
        this.mainPageParent = mainPageParent;
        this.scene = new Scene(mainPageParent);
    }

    public void onLogInPush(ActionEvent actionEvent) {
        EmployeeAtOffice employeeAtOffice = service.findEmployeeByEmailAndPassword(emailTF.getText(), passwordTF.getText());
        try {
            serviceProxy.logInEmployee(employeeAtOffice.getEmail(), employeeAtOffice.getPassword(), client);
            Stage mainPage = new Stage();
            mainPage.setTitle(employeeAtOffice.getName());
            mainPage.setScene(scene);

            client.setStage(mainPage);
            client.setShows();
            client.setEmployeeAtOffice(employeeAtOffice.getId());
            client.setLogInStage(stage);
            mainPage.show();
            stage.close();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log in error");
            alert.setHeaderText("Email or password is invalid!");
            alert.show();
        }
    }

}
