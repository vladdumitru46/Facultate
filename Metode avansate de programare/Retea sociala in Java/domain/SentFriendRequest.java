package com.example.reteasocialagui.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SentFriendRequest {
    private User user1;
    private User user2;
    private String status;
    private LocalDateTime time;

    public SentFriendRequest(User user1, User user2, String status, LocalDateTime time) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
        this.time = time;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return user1.getFirstName() + " " + user1.getLastName() +
                ", status: " + status +
                ", from: " + time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
