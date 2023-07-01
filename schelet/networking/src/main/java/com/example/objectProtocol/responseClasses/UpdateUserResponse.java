package com.example.objectProtocol.responseClasses;

import com.example.User;
import com.example.objectProtocol.interfaces.UpdateResponse;

public class UpdateUserResponse implements UpdateResponse {
    private final User user;

    public UpdateUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
