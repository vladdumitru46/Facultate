package com.example.objectProtocol.requestClasses;

import com.example.Boss;
import com.example.objectProtocol.interfaces.Request;

public class LogInRequestBoss implements Request {
    private final Boss boss;

    public LogInRequestBoss(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
