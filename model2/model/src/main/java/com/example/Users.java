package com.example;

public class Users extends Entity<Integer> {

    private String name;
    private String userName;

    public Users(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public Users() {
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
