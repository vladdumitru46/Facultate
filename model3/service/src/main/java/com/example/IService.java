package com.example;

import java.util.List;

public interface IService {
    //pui ce iti trebe in service

    void logIn(String connectionString, IServiceObserver client) throws Exception;

    List<Player> getLoggedInPlayers(Player player) throws Exception;

    void verifyPosition(String text, Game game) throws Exception;

    List<String> last2Positions(Player player) throws Exception;

    void addPosition(String position) throws Exception;
}
