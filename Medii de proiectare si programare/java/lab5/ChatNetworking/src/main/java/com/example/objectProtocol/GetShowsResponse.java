package com.example.objectProtocol;

import com.example.Show;

import java.util.List;

public class GetShowsResponse implements Response {
    private final List<Show> list;

    public GetShowsResponse(List<Show> list) {
        this.list = list;
    }

    public List<Show> getList() {
        return list;
    }
}
