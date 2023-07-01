package com.example.objectProtocol.responseClasses;

import com.example.Users;
import com.example.objectProtocol.interfaces.Response;

import java.util.List;

public class GetLoggedInUsersResponse implements Response {

    private final List<Users> list;

    public GetLoggedInUsersResponse(List<Users> list) {
        this.list = list;
    }

    public List<Users> getList() {
        return list;
    }
}
