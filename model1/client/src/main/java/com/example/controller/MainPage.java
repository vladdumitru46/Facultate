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

import java.time.LocalDateTime;
import java.util.Random;

public class MainPage implements IServiceObserver {

    @FXML
    GridPane gridPane;
    Service service;
    IService serverProxy;
    private Game game;
    private Clue clue;

    @FXML
    TableView<Game> tableView;
    private String playerAlias;


    private ObservableList<Game> observableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<Game, String> alias;
    @FXML
    TableColumn<Game, LocalDateTime> dateAndTIme;

    @FXML
    TableColumn<Game, Integer> numberOfTries;

    @FXML
    TableColumn<Game, String> textOfClue;


    public void setProxy(IService server) {
        this.serverProxy = server;

    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void updateScores(Game game) {
        Platform.runLater(() -> {
            observableList.setAll(service.getAllGames());
            tableView.setItems(observableList);
        });
    }

    public void initializeTable() {
        alias.setCellValueFactory(new PropertyValueFactory<>("playerAlias"));
        dateAndTIme.setCellValueFactory(new PropertyValueFactory<>("startTimeAndDate"));
        numberOfTries.setCellValueFactory(new PropertyValueFactory<>("numberOfTries"));
        textOfClue.setCellValueFactory(new PropertyValueFactory<>("textOfClue"));
        observableList.setAll(service.getAllGames());
        tableView.setItems(observableList);

    }

    public void initializeGrid() {
        Random random = new Random();
        int randomNumber = random.nextInt(service.getAllClues().toArray().length) + 1;
        clue = service.getClueById(randomNumber);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String string = String.valueOf(i) + String.valueOf(j);
                Label label = new Label(string);
                gridPane.add(label, i, j);
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

    int k = 0;
    private String positionsPlayed = new String();

    public void onClickCell(MouseEvent mouseEvent, Label label) throws Exception {

        if (k < 4) {
            positionsPlayed += label.getText() + " ";
            int i = Character.getNumericValue(label.getText().charAt(0));
            int j = Character.getNumericValue(label.getText().charAt(1));
            System.out.println("i" + i + "j" + j);
            if (i == clue.getPositionLine() && j == clue.getPositionColumn()) {

                Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                newAlert.setHeaderText("Victory");
                newAlert.setContentText("Congratulations! You have won!!\nThe clue is in label:" + label.getText() + "\n The clue is: " + clue.getTextOfClue() + "\n You have guessed " + (k + 1) + " times");
                newAlert.show();
                game.setNumberOfTries(k + 1);
                game.setTextOfClue(clue.getTextOfClue());
                GameForPlayer gameForPlayer = new GameForPlayer(playerAlias, game.getId(), k + 1, positionsPlayed, clue.getTextOfClue());
                service.addGameForPlayer(gameForPlayer);
                serverProxy.updateGame(game);
                k = 5;
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Clue");
                double euclideanDistance = Math.sqrt(Math.pow(i - clue.getPositionLine(), 2) + Math.pow(j - clue.getPositionColumn(), 2));
                alert.setContentText(String.valueOf(euclideanDistance));
                alert.show();
            }
            k++;
        }
        if (k == 4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("You lost");
            alert.setContentText(String.valueOf("You have lost!\nThe clue is in label: " + clue.getPositionLine() + clue.getPositionColumn() + "\n The clue is: " + clue.getTextOfClue() + "\n You have guessed 10 times"));
            alert.show();

            game.setNumberOfTries(10);
            game.setTextOfClue("");
            serverProxy.updateGame(game);

            GameForPlayer gameForPlayer = new GameForPlayer(playerAlias, game.getId(), 10, positionsPlayed, clue.getTextOfClue());
            service.addGameForPlayer(gameForPlayer);
        }


    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayerAlias(String alias) {
        this.playerAlias = alias;
    }

}
