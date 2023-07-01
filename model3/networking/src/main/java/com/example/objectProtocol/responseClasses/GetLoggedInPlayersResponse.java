package com.example.objectProtocol.responseClasses;

import com.example.Player;
import com.example.objectProtocol.interfaces.Response;

import java.util.List;

public class GetLoggedInPlayersResponse implements Response {

    private final List<Player> list;

    public GetLoggedInPlayersResponse(List<Player> list) {
        this.list = list;
    }

    public List<Player> getList() {
        return list;
    }
}
