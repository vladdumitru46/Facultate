package com.example.objectProtocol;

import com.example.Boss;

public class GetLoggedInEmployeesRequest implements Request {
    private final Boss boss;

    public GetLoggedInEmployeesRequest(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
