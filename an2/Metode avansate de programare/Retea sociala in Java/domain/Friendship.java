package com.example.reteasocialagui.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Friendship {
    public User user1;
    public User user2;
    LocalDateTime time;
    String status;

    public Friendship(User user1, User user2, LocalDateTime time) {
        this.user1 = user1;
        this.user2 = user2;
        this.time = time;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + user2.getId() + ", Nume: " + user2.getFirstName() + ", Prenume: " + user2.getLastName() +
                ", friends from: " + time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
