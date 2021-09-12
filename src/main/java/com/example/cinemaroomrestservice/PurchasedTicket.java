package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchasedTicket {

    private String token;
    @JsonProperty("ticket")
    private Seat seat;

    public PurchasedTicket(String token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
