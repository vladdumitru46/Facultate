package com.example.reteasocialagui.example.events;

import com.example.reteasocialagui.example.domain.Messages;

public class MessageChangeEvent implements Event {
    private final ChangeEventType type;
    private final Messages data;
    private Messages oldData;

    public MessageChangeEvent(ChangeEventType type, Messages data) {
        this.type = type;
        this.data = data;
    }

    public MessageChangeEvent(ChangeEventType type, Messages data, Messages oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Messages getData() {
        return data;
    }

    public Messages getOldData() {
        return oldData;
    }
}
