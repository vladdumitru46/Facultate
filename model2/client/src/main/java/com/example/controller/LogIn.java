package com.example.controller;

import com.example.IService;
import com.example.Service;
import com.example.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogIn {
    Service service;
    IService serviceProxy;
    MainPage mainPage;

    @FXML
    TextField userNameTF;
    private Stage stage = new Stage();
    private Scene scene;

    public void onLogInPush(ActionEvent actionEvent) {
        try {
            serviceProxy.logIn(userNameTF.getText(), mainPage);
            Stage mainStage = new Stage();
            mainPage.setService(service);
            mainPage.setProxy(serviceProxy);
            mainStage.setTitle("Game");
            mainStage.setScene(scene);
            Users users = service.getUserByUserName(userNameTF.getText());
            mainPage.setUser(users);
            mainStage.show();

            this.stage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setProxy(IService server) {
        this.serviceProxy = server;
    }

    public void setClient(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setParent(Parent parent) {
        this.scene = new Scene(parent);
    }

    public void setService(Service service) {
        this.service = service;
    }
}