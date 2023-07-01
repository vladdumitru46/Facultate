package com.example;

import com.example.repository.RepoGameForPlayer;

public class Main {
    public static void main(String[] args) {
        RepoGameForPlayer repoGameForPlayer = new RepoGameForPlayer();
        System.out.println(repoGameForPlayer.findAll());
    }
}