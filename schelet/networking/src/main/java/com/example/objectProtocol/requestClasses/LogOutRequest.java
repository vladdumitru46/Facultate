package com.example.objectProtocol.requestClasses;

import com.example.User;
import com.example.objectProtocol.interfaces.Request;

public class LogOutRequest implements Request {
    private final User user;

    public LogOutRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
