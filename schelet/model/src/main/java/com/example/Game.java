package com.example;

import java.time.LocalTime;

public class Game extends Entity<Integer> {

    private String playerAlias;
    private Integer noOfPoints;
    private LocalTime startTime;
    private LocalTime finishTime;
    private Integer gameTime;

    private String poOfGropi;

    private String positionsOfPlayer;


    public Game(String playerAlias, Integer noOfPoints, LocalTime startTime, LocalTime finishTime, Integer gameTime, String poOfGropi, String positionsOfPlayer) {
        this.playerAlias = playerAlias;
        this.noOfPoints = noOfPoints;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.gameTime = gameTime;
        this.poOfGropi = poOfGropi;
        this.positionsOfPlayer = positionsOfPlayer;
    }

    public Game(String playerAlias, Integer noOfPoints, LocalTime startTime) {
        this.playerAlias = playerAlias;
        this.noOfPoints = noOfPoints;
        this.startTime = startTime;
    }

    public Game() {
    }

    public String getPlayerAlias() {
        return playerAlias;
    }

    public void setPlayerAlias(String playerAlias) {
        this.playerAlias = playerAlias;
    }

    public Integer getNoOfPoints() {
        return noOfPoints;
    }

    public void setNoOfPoints(Integer noOfPoints) {
        this.noOfPoints = noOfPoints;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }

    public String getPoOfGropi() {
        return poOfGropi;
    }

    public void setPoOfGropi(String poOfGropi) {
        this.poOfGropi = poOfGropi;
    }

    public String getPositionsOfPlayer() {
        return positionsOfPlayer;
    }

    public void setPositionsOfPlayer(String positionsOfPlayer) {
        this.positionsOfPlayer = positionsOfPlayer;
    }
}
