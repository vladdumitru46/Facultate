package com.example.controller;

import com.example.Game;
import com.example.IService;
import com.example.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class LogIn {
    Service service;
    IService serviceProxy;
    MainPage mainPage;

    @FXML
    TextField aliasTF;
    private Stage stage = new Stage();
    private Scene scene;

    public void onLogInPush(ActionEvent actionEvent) {
        try {
            serviceProxy.logIn(aliasTF.getText(), mainPage);
            Stage mainStage = new Stage();
            mainStage.setTitle("Game");
            mainStage.setScene(scene);
            Game game = new Game(aliasTF.getText(), 20, LocalDateTime.now());
            service.addGame(game);
            mainPage.setGame(game);
            mainPage.initializeTable();
            mainPage.setProxy(serviceProxy);
            mainPage.setService(service);
            mainPage.initializeGrid();
            mainPage.setPlayerAlias(aliasTF.getText());
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