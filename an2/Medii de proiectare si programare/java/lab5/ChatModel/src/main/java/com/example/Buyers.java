package com.example;

public class Buyers extends Entity<String> {
    String buyerName;
    Integer noTicketsBought;
    String showName;

    public Buyers(String buyerName, Integer noTicketsBought, String showName) {
        this.buyerName = buyerName;
        this.noTicketsBought = noTicketsBought;
        this.showName = showName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getNoTicketsBought() {
        return noTicketsBought;
    }

    public void setNoTicketsBought(Integer noTicketsBought) {
        this.noTicketsBought = noTicketsBought;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public String toString() {
        return "Buyers{" +
                "buyerName='" + buyerName + '\'' +
                ", noTicketsBought=" + noTicketsBought +
                ", showName='" + showName + '\'' +
                '}';
    }
}
