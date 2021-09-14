package com.example.cinemaroomrestservice;

public class PurchasedTicket {

    private final String token;
    private final Seat seat;

    public PurchasedTicket(String token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public String getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }
}
