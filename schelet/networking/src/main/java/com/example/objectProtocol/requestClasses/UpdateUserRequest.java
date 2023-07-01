package com.example.objectProtocol.requestClasses;

import com.example.User;
import com.example.objectProtocol.interfaces.Request;

public class UpdateUserRequest implements Request {
    private final User user;

    public UpdateUserRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
