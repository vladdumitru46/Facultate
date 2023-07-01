package com.example.controller;

import com.example.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;

public class MainPage implements IServiceObserver {

    Service service;
    IService serverProxy;

    @FXML
    Label user1Label;
    @FXML
    Label user2Label;
    @FXML
    Label wordLabel;
    @FXML
    TextField c1TF;
    @FXML
    TextField c2TF;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mainTab;
    @FXML
    private Tab gameTab;
    @FXML
    private Tab scoresTab;
    private Users users;
//
//    @FXML
//    TableView

    public void initialize() {
        gameTab.setDisable(true);
    }

    public void setProxy(IService server) {
        this.serverProxy = server;

    }

    public void setService(Service service) {
        this.service = service;
    }

    public void onStartGamePush(ActionEvent actionEvent) throws Exception {
        List<Users> list = serverProxy.getLoggedInUsers(users);
        Words word = service.getOneWord(list.size() % 3 + 1);
        if (list.size() % 3 == 0 && list.size() >= 3) {
            for (int i = list.size() - 3; i < list.size(); i++) {
                if (!Objects.equals(list.get(i).getId(), users.getId())) {
                    if (Objects.equals(user1Label.getText(), " ")) {
                        user1Label.setText(list.get(i).getUserName());
                    } else {
                        user2Label.setText(list.get(i).getUserName());
                    }
                    wordLabel.setText(word.getWord());
                }
            }
            gameTab.setDisable(false);
            tabPane.getSelectionModel().select(gameTab);
        }
    }

    public void onSubmitPush(ActionEvent actionEvent) {
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
