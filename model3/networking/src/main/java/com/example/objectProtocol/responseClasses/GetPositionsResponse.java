package com.example.objectProtocol.responseClasses;

import com.example.objectProtocol.interfaces.Response;

import java.util.List;

public class GetPositionsResponse implements Response {
    private final List<String> list;

    public GetPositionsResponse(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }
}
