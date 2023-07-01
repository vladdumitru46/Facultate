package com.example.objectProtocol.requestClasses;

import com.example.Game;
import com.example.objectProtocol.interfaces.Request;

public class GuessPositionRequest implements Request {
    private final String position;
    private final Game game;

    public GuessPositionRequest(String position, Game game) {
        this.position = position;
        this.game = game;
    }

    public String getPosition() {
        return position;
    }

    public Game getGame() {
        return game;
    }
}
