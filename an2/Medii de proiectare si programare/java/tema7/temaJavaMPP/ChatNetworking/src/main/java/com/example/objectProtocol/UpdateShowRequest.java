package com.example.objectProtocol;

import com.example.Show;

public class UpdateShowRequest implements Request {

    private final Show show;

    public UpdateShowRequest(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }
}
