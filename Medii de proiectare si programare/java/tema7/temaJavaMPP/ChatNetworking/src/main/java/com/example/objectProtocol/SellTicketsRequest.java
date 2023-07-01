package com.example.objectProtocol;

import com.example.Buyers;

public class SellTicketsRequest implements Request {
    private Buyers buyers;

    public SellTicketsRequest(Buyers buyers) {
        this.buyers = buyers;
    }

    public Buyers getBuyers() {
        return buyers;
    }
}
