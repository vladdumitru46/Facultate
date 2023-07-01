package com.example.objectProtocol;

public class FindEmployeeRequest implements Request {
    private final String email;
    private final String password;

    public FindEmployeeRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
