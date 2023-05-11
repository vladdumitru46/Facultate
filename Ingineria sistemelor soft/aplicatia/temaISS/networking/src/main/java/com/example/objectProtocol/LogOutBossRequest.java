package com.example.objectProtocol;

import com.example.Boss;

public class LogOutBossRequest implements Request {
    private final Boss boss;

    public LogOutBossRequest(Boss boss) {
        this.boss = boss;
    }

    public Boss getBoss() {
        return boss;
    }
}
