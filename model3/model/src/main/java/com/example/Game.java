package com.example;

public class Game extends Entity<Integer> {
    private Integer player1;
    private Integer player2;
    private String positionOfPlayer1Boat;
    private String positionOfPlayer2Boat;
    private String winner;
    private Integer noPositionsPlayedPlayer1;
    private Integer noPositionsPlayedPlayer2;

    public Game(Integer player1, Integer player2, String positionOfPlayer1Boat, String positionOfPlayer2Boat) {
        this.player1 = player1;
        this.player2 = player2;
        this.positionOfPlayer1Boat = positionOfPlayer1Boat;
        this.positionOfPlayer2Boat = positionOfPlayer2Boat;
    }

    public Game(Integer player1, Integer player2, String positionOfPlayer1Boat, String positionOfPlayer2Boat, String winner, Integer noPositionsPlayedPlayer1, Integer noPositionsPlayedPlayer2) {
        this.player1 = player1;
        this.player2 = player2;
        this.positionOfPlayer1Boat = positionOfPlayer1Boat;
        this.positionOfPlayer2Boat = positionOfPlayer2Boat;
        this.winner = winner;
        this.noPositionsPlayedPlayer1 = noPositionsPlayedPlayer1;
        this.noPositionsPlayedPlayer2 = noPositionsPlayedPlayer2;
    }

    public Game() {
    }

    public Integer getPlayer1() {
        return player1;
    }

    public void setPlayer1(Integer player1) {
        this.player1 = player1;
    }

    public Integer getPlayer2() {
        return player2;
    }

    public void setPlayer2(Integer player2) {
        this.player2 = player2;
    }

    public String getPositionOfPlayer1Boat() {
        return positionOfPlayer1Boat;
    }

    public void setPositionOfPlayer1Boat(String positionOfPlayer1Boat) {
        this.positionOfPlayer1Boat = positionOfPlayer1Boat;
    }

    public String getPositionOfPlayer2Boat() {
        return positionOfPlayer2Boat;
    }

    public void setPositionOfPlayer2Boat(String positionOfPlayer2Boat) {
        this.positionOfPlayer2Boat = positionOfPlayer2Boat;
    }

   
    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Integer getNoPositionsPlayedPlayer1() {
        return noPositionsPlayedPlayer1;
    }

    public void setNoPositionsPlayedPlayer1(Integer noPositionsPlayedPlayer1) {
        this.noPositionsPlayedPlayer1 = noPositionsPlayedPlayer1;
    }

    public Integer getNoPositionsPlayedPlayer2() {
        return noPositionsPlayedPlayer2;
    }

    public void setNoPositionsPlayedPlayer2(Integer noPositionsPlayedPlayer2) {
        this.noPositionsPlayedPlayer2 = noPositionsPlayedPlayer2;
    }
}
