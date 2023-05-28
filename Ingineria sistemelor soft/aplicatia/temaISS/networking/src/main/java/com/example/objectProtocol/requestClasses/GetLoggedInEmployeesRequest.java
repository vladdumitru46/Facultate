package com.example.objectProtocol.requestClasses;

import com.example.Boss;
import com.example.objectProtocol.interfaces.Request;

public class GetLoggedInEmployeesRequest implements Request {
    private final Boss boss;

    public GetLoggedInEmployeesRequest(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
