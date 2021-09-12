package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cinema {

    private int totalRows = 9;
    private int totalColumns = 9;
    private List<Seat> availableSeats = new ArrayList<>();
    @JsonIgnore
    private List<PurchasedTicket> purchasedTickets = new ArrayList<>();
    @JsonIgnore
    private final Stats stats = new Stats();

    {
        for (int i = 1; i <= this.totalRows; i++) {
            for (int j = 1; j <= this.totalColumns; j++) {
                this.availableSeats.add(new Seat(i, j));
            }
        }
        this.stats.setNumberOfAvailableSeats(this.availableSeats.size());
    }

    public void bookTicket(String token, Seat seat) throws IllegalArgumentException {
        //check if seat is already purchased
        for (PurchasedTicket pt : purchasedTickets) {
            if (pt.getSeat().equals(seat)) {
                throw new IllegalArgumentException("The ticket has been already purchased!");
            }
        }
        //check if given seat is out of bounds
        if (seat.getRow() < 1 || seat.getRow() > this.totalRows || seat.getColumn() < 1 || seat.getColumn() > this.totalColumns) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        this.availableSeats.remove(seat);
        this.purchasedTickets.add(new PurchasedTicket(token, seat));
        this.stats.purchaseUpdate(seat.getPrice());
    }

    public Seat returnTicket(String token) throws IllegalArgumentException {
        for (PurchasedTicket pt : purchasedTickets) {
            if (token.contains(pt.getToken())) {
                Seat seat = pt.getSeat();
                this.purchasedTickets.remove(pt);
                //temporary solution, add seat on last position in the list
                this.availableSeats.add(seat);
                Collections.sort(this.availableSeats);
                this.stats.returnUpdate(seat.getPrice());
                return seat;
            }
        }
        throw new IllegalArgumentException("Wrong token!");
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<PurchasedTicket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(List<PurchasedTicket> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    public Stats getStats() {
        return stats;
    }
}
