package com.example;

public class User extends Entity<Integer> {

    private String name;
    private String userName;

    private Integer no_of_points;
    private Integer gameTime;

    public User(String name, String userName, Integer no_of_points, Integer gameTime) {
        this.name = name;
        this.userName = userName;
        this.no_of_points = no_of_points;
        this.gameTime = gameTime;
    }

    public User() {
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

    public Integer getNo_of_points() {
        return no_of_points;
    }

    public void setNo_of_points(Integer no_of_points) {
        this.no_of_points = no_of_points;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }
}
