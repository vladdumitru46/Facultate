package com.example.interfaces;

import com.example.GameForPlayer;

import java.util.List;

public interface IRepoGameForPlayer extends IRepository<GameForPlayer, Integer> {

    List<GameForPlayer> getByPlayerAlias(String alias);
}
