package com.example.temampp;

import com.example.temampp.domains.EmployeeAtOffice;
import com.example.temampp.repositoryes.ArtistRepo;
import com.example.temampp.repositoryes.BuyerRepo;
import com.example.temampp.repositoryes.EmployeeRepo;
import com.example.temampp.repositoryes.ShowRepository;
import com.example.temampp.services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class LogIn {

    Service service = new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties()));

    @FXML
    TextField emailTF;
    @FXML
    TextField passwordTF;

    Stage stage = new Stage();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onLogInPush(ActionEvent actionEvent) throws IOException {
        if (service.logInEmployee(emailTF.getText(), passwordTF.getText())) {
            EmployeeAtOffice employeeAtOffice = service.findEmployeeByEmailAndPassword(emailTF.getText(), passwordTF.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent loader = fxmlLoader.load();
            MainPage mainPage = fxmlLoader.getController();
            Scene scene = new Scene(loader, 600, 400);
            mainPage.setService(new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties())));
            mainPage.setEmployeeName(employeeAtOffice.getId());
            mainPage.setStage(stage);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }
}
