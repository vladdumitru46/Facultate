package com.example;

public interface IService {

    void logIn(String alias, IServiceObserver client) throws Exception;

    void logOut(Player player, IServiceObserver client) throws Exception;

    Clue getClueByPosition(Integer positionLine, Integer positionColumn);


    void updateGame(Game game) throws Exception;
}
