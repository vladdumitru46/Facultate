package com.example.temampp.events;


import com.example.temampp.domains.Show;

public class ShowChangeEvent implements Event {
    private final ChangeEventType type;
    private final Show data;
    private Show oldData;

    public ShowChangeEvent(ChangeEventType type, Show data) {
        this.type = type;
        this.data = data;
    }

    public ShowChangeEvent(ChangeEventType type, Show data, Show oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Show getData() {
        return data;
    }

    public Show getOldData() {
        return oldData;
    }
}
