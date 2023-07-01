package com.example.objectProtocol.responseClasses;

import com.example.Game;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class UpdateScoreResponse implements UpdateResponse {
    private final Game game;

    public UpdateScoreResponse(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
