package com.example.controller;

import com.example.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Objects;

import static java.lang.String.valueOf;

public class MainPage implements IServiceObserver {

    @FXML
    GridPane gridPane;
    @FXML
    TextField positionOfBoatTF;
    @FXML
    TabPane tabPane;
    @FXML
    Tab mainTab;
    @FXML
    Tab gameTab;
    @FXML
    Label labelOpponent;
    Service service;
    IService serverProxy;
    private Player player;
    private Game game;

    public void setProxy(IService server) {
        this.serverProxy = server;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void onStartPush(ActionEvent actionEvent) throws Exception {
        serverProxy.addPosition(positionOfBoatTF.getText());
        List<Player> list = serverProxy.getLoggedInPlayers(new Player());
        if (list.size() % 2 == 0 && list.size() >= 2 && serverProxy.last2Positions(player).size() >= 2) {
            for (int i = list.size() - 2; i < list.size(); i++) {
                if (!Objects.equals(list.get(i).getId(), player.getId())) {
                    labelOpponent.setText("Opponent: " + list.get(i).getId());
                    String position1 = serverProxy.last2Positions(player).get(0);
                    String position2 = serverProxy.last2Positions(player).get(1);
                    Game game = new Game(player.getId(), list.get(i).getId(), position1, position2);
                    service.addGame(game);
                    this.game = game;
                }
            }
            initializeGrid();
            tabPane.getSelectionModel().select(gameTab);
        }
    }

    public void initializeGrid() {
        String position1 = valueOf(positionOfBoatTF.getText().charAt(0)) + positionOfBoatTF.getText().charAt(1);
        String position2 = valueOf(positionOfBoatTF.getText().charAt(2)) + positionOfBoatTF.getText().charAt(3);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String string = valueOf(i) + valueOf(j);
                if (string.equals(position1) || string.equals(position2)) {
                    Label label = new Label("BOAT");
                    gridPane.add(label, j, i);
                    label.setOnMouseClicked(event -> {
                        try {
                            onClickCell(event, label);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
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
    }

    int noPositoons = 0;

    private void onClickCell(MouseEvent event, Label label) throws Exception {
        noPositoons += 1;
        int rowIndex = GridPane.getRowIndex(label);
        int columnIndex = GridPane.getColumnIndex(label);
        String string = valueOf(rowIndex) + columnIndex;
        serverProxy.verifyPosition(string, game);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void nottifyDecision(String s, Game game, String guess) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(s);
            alert.show();
            if (s.equals("Te-a lovit!") || s.equals("Ai ghicit!")) {
                for (int i = 0; i < gridPane.getChildren().size(); i++) {
                    Label label = (Label) gridPane.getChildren().get(i);
                    Integer row = GridPane.getRowIndex(label);
                    Integer column = GridPane.getColumnIndex(label);
                    if (row != null && column != null && row == Character.getNumericValue(guess.charAt(0)) && column == Character.getNumericValue(guess.charAt(1))) {
                        label.setText("LOVIT!");
                        verrifyIfGameIsFinished(game);
                    }

                }
            }
        });

    }

    private void verrifyIfGameIsFinished(Game game) {
        int ok = 0;
        String position1 = String.valueOf(game.getPositionOfPlayer1Boat().charAt(0)) + game.getPositionOfPlayer1Boat().charAt(1);
        String position2 = String.valueOf(game.getPositionOfPlayer1Boat().charAt(2)) + game.getPositionOfPlayer1Boat().charAt(3);
        for (int i = 0; i < gridPane.getChildren().size(); i++) {
            Label label = (Label) gridPane.getChildren().get(i);
            Integer row = GridPane.getRowIndex(label);
            Integer column = GridPane.getColumnIndex(label);
            if (row == Character.getNumericValue(position1.charAt(0)) && column == Character.getNumericValue(position1.charAt(1))) {
                if (Objects.equals(label.getText(), "LOVIT!")) {
                    ok++;
                }
            }
            if (row == Character.getNumericValue(position2.charAt(0)) && column == Character.getNumericValue(position2.charAt(1))) {
                if (Objects.equals(label.getText(), "LOVIT!")) {
                    ok++;
                }
            }
            if (ok == 2) {
                gameTab.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("GAME OVER!");
                alert.showAndWait();
                Player player1 = service.getPlayerById(game.getPlayer2());
                game.setWinner(player1.getName());
                game.setNoPositionsPlayedPlayer1(noPositoons);
                game.setNoPositionsPlayedPlayer2(noPositoons);
                service.updateGame(game);
                break;
            }
        }
    }
}
