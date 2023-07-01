package com.example.objectProtocol.requestClasses;

import com.example.Users;
import com.example.objectProtocol.interfaces.Request;

public class LogInRequest implements Request {

    private final Users users;

    public LogInRequest(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }
}
