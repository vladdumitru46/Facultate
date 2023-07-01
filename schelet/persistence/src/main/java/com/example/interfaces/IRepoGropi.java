package com.example.interfaces;

import com.example.Game;

import java.util.List;

public interface IRepoGropi extends IRepository<Game, Integer> {
    List<Game> findAllGamesForPlayer(String alias);

    Game addPositionToGame(Integer id, String position);
}
