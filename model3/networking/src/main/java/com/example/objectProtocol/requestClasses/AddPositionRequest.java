package com.example.objectProtocol.requestClasses;

import com.example.objectProtocol.interfaces.Request;

public class AddPositionRequest implements Request {

    private final String position;

    public AddPositionRequest(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
