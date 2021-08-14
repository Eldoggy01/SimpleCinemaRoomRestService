package com.khatsukov.cinemaroomrestservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * todo Document type Theater
 */
@Component
public class CinemaRoom {

    private final int totalRows = 9;
    private final int totalColumns = 9;
    private List<Seat> allSeatsList = new ArrayList<>();

    @JsonIgnore
    public List<Seat> getAllSeatsList() {
        return allSeatsList;
    }

    public Optional<Seat> getSeat(int row, int column) {
        return allSeatsList.stream().filter(item -> item.getRow() == row && item.getColumn() == column).findFirst();
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Seat> getAvailableSeatsList() {
        //Возможно стоит переделать.
        return allSeatsList.stream().filter(seat -> !seat.isPurchased()).collect(Collectors.toUnmodifiableList());
    }

    public CinemaRoom() {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                allSeatsList.add(new Seat(i, j));
            }
        }
    }
}
