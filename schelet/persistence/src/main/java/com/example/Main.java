package com.example;

import com.example.repository.RepoUser;

public class Main {
    public static void main(String[] args) {
        RepoUser repoUser = new RepoUser();
        System.out.println(repoUser.findOneByAlias("aa"));
    }
}