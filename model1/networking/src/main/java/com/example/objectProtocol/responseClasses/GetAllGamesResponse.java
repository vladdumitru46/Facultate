package com.example.objectProtocol.responseClasses;

import com.example.Game;
import com.example.objectProtocol.interfaces.UpdateResponse;

import java.util.List;

public class GetAllGamesResponse implements UpdateResponse {
    private List<Game> list;

    public GetAllGamesResponse(List<Game> list) {
        this.list = list;
    }

    public List<Game> getList() {
        return list;
    }
}
