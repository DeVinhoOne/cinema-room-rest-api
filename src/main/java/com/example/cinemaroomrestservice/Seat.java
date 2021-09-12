package com.example.cinemaroomrestservice;

import java.util.Objects;

public class Seat implements Comparable<Seat> {

    private int row;
    private int column;
    private int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Seat)) {
            return false;
        }
        Seat seat = (Seat) o;
        return this.row == seat.getRow() && this.column == seat.getColumn() && this.price == seat.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.column, this.price);
    }

    @Override
    public int compareTo(Seat s) {
        if (this.row > s.row) {
            return 1;
        } else if (this.row < s.row) {
            return -1;
        }
        return Integer.compare(this.column, s.column);
    }
}
