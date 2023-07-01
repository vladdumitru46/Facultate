package com.example.objectProtocol;

public class FindEmployeeRequest implements Request {
    private String email;
    private String password;

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
