package com.example;

import com.example.repository.RepoGame;

public class main {
    public static void main(String[] args) {
        RepoGame repoGame = new RepoGame();
        System.out.println(repoGame.findAll());
    }
}
