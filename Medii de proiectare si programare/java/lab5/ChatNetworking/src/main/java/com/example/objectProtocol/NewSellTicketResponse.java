package com.example.objectProtocol;

import com.example.Buyers;

public class NewSellTicketResponse implements UpdateResponse {
    private final Buyers buyers;

    public NewSellTicketResponse(Buyers buyers) {
        this.buyers = buyers;
    }

    public Buyers getBuyers() {
        return buyers;
    }
}
