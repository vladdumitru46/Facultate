package com.example.objectProtocol.requestClasses;

import com.example.Users;
import com.example.objectProtocol.interfaces.Request;

public class GetLoggedInUsers implements Request {
    private final Users users;

    public GetLoggedInUsers(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }
}
