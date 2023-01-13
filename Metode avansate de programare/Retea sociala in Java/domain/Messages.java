package com.example.reteasocialagui.example.domain;

public class Messages {
    private User user1;
    private User user2;
    private String message;

    public Messages(User user1, User user2, String message) {
        this.user1 = user1;
        this.user2 = user2;
        this.message = message;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + user1.getFirstName() + " " + user1.getLastName() + "]: " + message;
    }
}
