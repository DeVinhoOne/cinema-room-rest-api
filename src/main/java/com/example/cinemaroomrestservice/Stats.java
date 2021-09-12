package com.example.cinemaroomrestservice;

public class Stats {

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public void purchaseUpdate(int price) {
        this.numberOfPurchasedTickets++;
        this.numberOfAvailableSeats--;
        this.currentIncome += price;
    }

    public void returnUpdate(int price) {
        this.numberOfPurchasedTickets--;
        this.numberOfAvailableSeats++;
        this.currentIncome -= price;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }
}
