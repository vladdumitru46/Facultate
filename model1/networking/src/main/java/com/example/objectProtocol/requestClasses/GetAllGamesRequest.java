package com.example.objectProtocol.requestClasses;

import com.example.Player;
import com.example.objectProtocol.interfaces.Request;

public class GetAllGamesRequest implements Request {
    private final Player player;

    public GetAllGamesRequest(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
