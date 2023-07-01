package com.example.interfaces;

import com.example.Game;

import java.util.List;

public interface IRepoGame extends IRepository<Game, Integer> {
    boolean findIfPlayerIsInAnActiveGame(Integer playerId);

    List<Game> allGamesByPlayer(Integer id);
}
