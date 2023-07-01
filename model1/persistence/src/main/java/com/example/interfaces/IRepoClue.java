package com.example.interfaces;

import com.example.Clue;

public interface IRepoClue extends IRepository<Clue, Integer> {

    Clue findOneByPosition(Integer positionLine, Integer positionColumn);
}
