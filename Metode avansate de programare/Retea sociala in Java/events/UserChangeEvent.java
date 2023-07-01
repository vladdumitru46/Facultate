package com.example.reteasocialagui.example.events;

import com.example.reteasocialagui.example.domain.UserFriendsFrom;

public class UserChangeEvent implements Event {
    private final ChangeEventType type;
    private final UserFriendsFrom data;
    private UserFriendsFrom oldData;

    public UserChangeEvent(ChangeEventType type, UserFriendsFrom data) {
        this.type = type;
        this.data = data;
    }

    public UserChangeEvent(ChangeEventType type, UserFriendsFrom data, UserFriendsFrom oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public UserFriendsFrom getData() {
        return data;
    }

    public UserFriendsFrom getOldData() {
        return oldData;
    }
}
