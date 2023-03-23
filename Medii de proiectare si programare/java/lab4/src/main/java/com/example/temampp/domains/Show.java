package com.example.temampp.domains;

import java.time.LocalDate;

public class Show extends Entity<String> {
    private String showName;
    private String artistName;
    private LocalDate date;
    private String placeOfShow;
    private Integer noTicketsAvailable;
    private Integer noTicketsSold;

    public Show(String showName, String artist, LocalDate date, String placeOfShow, Integer noTicketsAvailable, Integer noTicketsSold) {
        this.showName = showName;
        this.artistName = artist;
        this.date = date;
        this.placeOfShow = placeOfShow;
        this.noTicketsAvailable = noTicketsAvailable;
        this.noTicketsSold = noTicketsSold;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlaceOfShow() {
        return placeOfShow;
    }

    public void setPlaceOfShow(String placeOfShow) {
        this.placeOfShow = placeOfShow;
    }

    public Integer getNoTicketsAvailable() {
        return noTicketsAvailable;
    }

    public void setNoTicketsAvailable(Integer noTicketsAvailable) {
        this.noTicketsAvailable = noTicketsAvailable;
    }

    public Integer getNoTicketsSold() {
        return noTicketsSold;
    }

    public void setNoTicketsSold(Integer noTicketsSold) {
        this.noTicketsSold = noTicketsSold;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showName='" + showName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", date=" + date +
                ", placeOfShow='" + placeOfShow + '\'' +
                ", noTicketsAvailable=" + noTicketsAvailable +
                ", noTicketsSold=" + noTicketsSold +
                '}';
    }
}
