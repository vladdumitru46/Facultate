package com.example.objectProtocol;

import com.example.Boss;

public class LogInRequestBoss implements Request {
    private final Boss boss;

    public LogInRequestBoss(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
