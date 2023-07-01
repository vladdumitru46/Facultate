package com.example.objectProtocol.requestClasses;

import com.example.User;
import com.example.objectProtocol.interfaces.Request;

public class LogInRequest implements Request {
    private final User user;

    public LogInRequest(User player) {
        this.user = player;
    }

    public User getUser() {
        return user;
    }
}
