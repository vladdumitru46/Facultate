package com.example;

public class Player extends Entity<Integer>{
    private String name;
    private String userName;

    public Player(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
