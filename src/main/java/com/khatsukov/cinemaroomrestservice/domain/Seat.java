package com.khatsukov.cinemaroomrestservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * todo Document type Seat
 */

public class Seat {
    private int row;
    private int column;
    private boolean isPurchased = false;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }
}
