package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Cinema {

    private static int totalRows = 9;
    private static int totalColumns = 9;
    private List<Seat> availableSeats = new ArrayList<>();
    private List<PurchasedTicket> purchasedTickets = new ArrayList<>();
    @JsonIgnore
    private Stats stats = new Stats();

    {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j));
            }
        }
        stats.setNumberOfAvailableSeats(availableSeats.size());
    }

    public PurchasedTicket bookTicket(UUID token, Seat seat) throws IllegalArgumentException {
        //check if seat is already purchased
        for (PurchasedTicket pt : purchasedTickets) {
            if (pt.getSeat().equals(seat)) {
                throw new IllegalArgumentException("The ticket has been already purchased!");
            }
        }
        //check if given seat is out of bounds
        if (seat.getRow() < 1 || seat.getRow() > totalRows || seat.getColumn() < 1 || seat.getColumn() > totalColumns) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        availableSeats.remove(seat);
        PurchasedTicket ticket = new PurchasedTicket(token.toString(), seat);
        purchasedTickets.add(ticket);
        stats.purchaseUpdate(seat.getPrice());
        return ticket;
    }

    public Seat returnTicket(String token) throws IllegalArgumentException {
        for (PurchasedTicket pt : purchasedTickets) {
            if (token.contains(pt.getToken())) {
                Seat seat = pt.getSeat();
                purchasedTickets.remove(pt);
                availableSeats.add(seat);
                Collections.sort(availableSeats);
                stats.returnUpdate(seat.getPrice());
                return seat;
            }
        }
        throw new IllegalArgumentException("Wrong token!");
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return new ArrayList<>(availableSeats);
    }

    public Stats getStats() {
        return stats;
    }
}
