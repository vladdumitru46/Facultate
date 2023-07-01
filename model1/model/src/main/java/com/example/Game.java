package com.example;

import java.time.LocalDateTime;

public class Game extends Entity<Integer> {

    private String playerAlias;
    private Integer numberOfTries;
    private LocalDateTime startTimeAndDate;

    private String textOfClue;

    public Game(String playerAlias, Integer numberOfTries, LocalDateTime startTimeAndDate, String textOfClue) {
        this.playerAlias = playerAlias;
        this.numberOfTries = numberOfTries;
        this.startTimeAndDate = startTimeAndDate;
        this.textOfClue = textOfClue;
    }

    public Game(String playerAlias, LocalDateTime startTimeAndDate) {
        this.playerAlias = playerAlias;
        this.startTimeAndDate = startTimeAndDate;
    }

    public Game() {
    }

    public Game(String playerAlias, Integer numberOfTries, LocalDateTime startTimeAndDate) {
        this.playerAlias = playerAlias;
        this.numberOfTries = numberOfTries;
        this.startTimeAndDate = startTimeAndDate;
    }

    public String getPlayerAlias() {
        return playerAlias;
    }

    public void setPlayerAlias(String playerAlias) {
        this.playerAlias = playerAlias;
    }

    public Integer getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(Integer numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public LocalDateTime getStartTimeAndDate() {
        return startTimeAndDate;
    }

    public void setStartTimeAndDate(LocalDateTime startTimeAndDate) {
        this.startTimeAndDate = startTimeAndDate;
    }

    public String getTextOfClue() {
        return textOfClue;
    }

    public void setTextOfClue(String textOfClue) {
        this.textOfClue = textOfClue;
    }

    @Override
    public String toString() {
        return "Game{" +
                "playerAlias='" + playerAlias + '\'' +
                ", numberOfTries=" + numberOfTries +
                ", startTimeAndDate=" + startTimeAndDate +
                ", textOfClue='" + textOfClue + '\'' +
                '}';
    }
}
