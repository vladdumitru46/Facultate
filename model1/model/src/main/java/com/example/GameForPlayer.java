package com.example;

public class GameForPlayer extends Entity<Integer> {
    private String playerAlias;
    private Integer idGame;
    private Integer numberOfTries;
    private String proposedPositions;
    private String textOfClue;

    public GameForPlayer(String playerAlias, Integer idGame, Integer numberOfTries, String proposedPositions, String textOfClue) {
        this.playerAlias = playerAlias;
        this.idGame = idGame;
        this.numberOfTries = numberOfTries;
        this.proposedPositions = proposedPositions;
        this.textOfClue = textOfClue;
    }

    public GameForPlayer() {
    }

    public String getPlayerAlias() {
        return playerAlias;
    }

    public void setPlayerAlias(String playerAlias) {
        this.playerAlias = playerAlias;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(Integer numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public String getProposedPositions() {
        return proposedPositions;
    }

    public void setProposedPositions(String proposedPositions) {
        this.proposedPositions = proposedPositions;
    }

    public String getTextOfClue() {
        return textOfClue;
    }

    public void setTextOfClue(String textOfClue) {
        this.textOfClue = textOfClue;
    }
}

