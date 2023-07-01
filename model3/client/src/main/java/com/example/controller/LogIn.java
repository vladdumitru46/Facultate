package com.example.controller;

import com.example.IService;
import com.example.Player;
import com.example.Service;
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
            Player player = service.getPlayerByUserName(userNameTF.getText());
            Stage mainStage = new Stage();
            mainStage.setTitle("Game");
            mainStage.setScene(scene);
            mainPage.setPlayer(player);
            mainPage.setProxy(serviceProxy);
            mainPage.setService(service);
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