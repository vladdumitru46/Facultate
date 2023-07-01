package com.example.objectProtocol.responseClasses;

import com.example.Game;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class GuessPositionResponse implements UpdateResponse {

    private final String position;
    private final Game game;

    private final String guess;

    public GuessPositionResponse(String position, Game game, String guess) {
        this.position = position;
        this.game = game;
        this.guess = guess;
    }

    public String getPosition() {
        return position;
    }

    public Game getGame() {
        return game;
    }

    public String getGuess() {
        return this.guess;
    }
}
