package com.example.reteasocialagui.example.events;

import com.example.reteasocialagui.example.domain.FriendRequests;

public class FriendRequestChangeEvent implements Event {
    private final ChangeEventType type;
    private final FriendRequests data;
    private FriendRequests oldData;

    public FriendRequestChangeEvent(ChangeEventType type, FriendRequests data) {
        this.type = type;
        this.data = data;
    }

    public FriendRequestChangeEvent(ChangeEventType type, FriendRequests data, FriendRequests oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public FriendRequests getData() {
        return data;
    }

    public FriendRequests getOldData() {
        return oldData;
    }
}
