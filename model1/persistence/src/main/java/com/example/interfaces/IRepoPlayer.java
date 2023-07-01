package com.example.interfaces;

import com.example.Player;

public interface IRepoPlayer extends IRepository<Player, Integer> {

    Player findOneByAlias(String alias);

}
