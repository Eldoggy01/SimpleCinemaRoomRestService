package com.khatsukov.cinemaroomrestservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * todo Document type Ticket
 */
public class Ticket {
   private UUID token;
   private Seat seat;

    public Ticket(UUID token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    @JsonProperty("token")
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
