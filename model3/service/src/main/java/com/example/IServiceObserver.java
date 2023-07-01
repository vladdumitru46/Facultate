package com.example;

public interface IServiceObserver {
    void nottifyDecision(String s, Game game, String guess) throws Exception;

    //pui ce iti trebuie ca sa faci update
}
