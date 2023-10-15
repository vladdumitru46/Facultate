package com.example.objectProtocol.responseClasses;

import com.example.objectProtocol.interfaces.Response;

public class ErrorResponse implements Response {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
