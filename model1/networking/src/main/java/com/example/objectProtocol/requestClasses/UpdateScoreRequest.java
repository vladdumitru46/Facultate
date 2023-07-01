package com.example.objectProtocol.requestClasses;

import com.example.Game;
import com.example.objectProtocol.interfaces.Request;

public class UpdateScoreRequest implements Request {

    private final Game game;

    public UpdateScoreRequest(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
