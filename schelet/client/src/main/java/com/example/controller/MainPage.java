package com.example.controller;

import com.example.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPage implements IServiceObserver {


    Service service;
    IService serverProxy;

    @FXML
    GridPane gridPane;
    @FXML
    TableView<User> tableView;
    @FXML
    TableColumn<User, String> alias;
    @FXML
    TableColumn<User, Integer> noOfPoints;
    @FXML
    TableColumn<User, LocalTime> gameTime;
    List<String> positions = new ArrayList<>();
    private Game game;
    int points = 0;
    User user;

    int contor = 0;
    private String positionsPlayed = "";
    private ObservableList<User> observableList = FXCollections.observableArrayList();

    public void initializeTable() {
        alias.setCellValueFactory(new PropertyValueFactory<>("userName"));
        noOfPoints.setCellValueFactory(new PropertyValueFactory<>("no_of_points"));
        gameTime.setCellValueFactory(new PropertyValueFactory<>("gameTime"));
        observableList.setAll(service.getAllUsers());
        tableView.setItems(observableList);
    }

    public void initializeGrid() {
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(4);
            String pos = String.valueOf(i) + randomNumber;
            positions.add(pos);
            for (int j = 0; j < 4; j++) {
                String string = String.valueOf(i) + String.valueOf(j);
                Label label = new Label(string);
                gridPane.add(label, j, i);
                label.setOnMouseClicked(event -> {
                    try {
                        onClickCell(event, label);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private void onClickCell(MouseEvent event, Label label) throws Exception {

        String positionsOfGropi = "";
        for (var j : positions) {
            positionsOfGropi += j;
        }
        if (contor < 4) {
            points += Character.getNumericValue(label.getText().charAt(0)) + 1;
            positionsPlayed += label.getText() + " ";
            for (var i : positions) {
                if (label.getText().equals(i)) {

                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setHeaderText("Lost");
                    newAlert.setContentText("You have Lost!");
                    newAlert.show();
                    game.setPositionsOfPlayer(positionsPlayed);
                    game.setFinishTime(LocalTime.now());
                    game.setNoOfPoints(points);
                    game.setPoOfGropi(positionsOfGropi);
                    game.setGameTime(game.getFinishTime().getSecond() + game.getStartTime().getSecond());
                    service.updateGame(game);
                    user.setNo_of_points(user.getNo_of_points() + points);
                    user.setGameTime(user.getGameTime() + game.getGameTime());
                    serverProxy.updateUser(user);
                    contor = 5;
                }
            }
            contor++;
        }
        if (contor == 4) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setHeaderText("Game Over");
            newAlert.setContentText("You have WON!");
            newAlert.show();
            game.setPositionsOfPlayer(positionsPlayed);
            game.setFinishTime(LocalTime.now());
            game.setNoOfPoints(points);
            game.setPoOfGropi(positionsOfGropi);
            game.setGameTime(game.getFinishTime().getSecond() + game.getStartTime().getSecond());
            service.updateGame(game);
            user.setNo_of_points(user.getNo_of_points() + points);
            user.setGameTime(user.getGameTime() + game.getGameTime());
            serverProxy.updateUser(user);
        }

    }

    public void setProxy(IService server) {
        this.serverProxy = server;

    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void updateTable(User user) {
        Platform.runLater(() -> {
            observableList.setAll(service.getAllUsers());
            tableView.setItems(observableList);
        });
    }
}
