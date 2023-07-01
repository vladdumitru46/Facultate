package com.example.objectProtocol;

import com.example.Buyers;

public class SellTicketResponse implements UpdateResponse {
    private final Buyers buyers;

    public SellTicketResponse(Buyers buyers) {
        this.buyers = buyers;
    }

    public Buyers getBuyers() {
        return buyers;
    }
}
