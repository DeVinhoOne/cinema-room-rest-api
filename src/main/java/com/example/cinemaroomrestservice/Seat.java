package com.example.cinemaroomrestservice;

import java.util.Objects;

public class Seat implements Comparable<Seat> {

    private final int row;
    private final int column;
    private final int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getPrice() {
        return price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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
        return row == seat.getRow() && column == seat.getColumn() && price == seat.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price);
    }

    @Override
    public int compareTo(Seat s) {
        if (row > s.row) {
            return 1;
        } else if (row < s.row) {
            return -1;
        }
        return Integer.compare(column, s.column);
    }
}
