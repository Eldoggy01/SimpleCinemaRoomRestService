package com.khatsukov.cinemaroomrestservice.data;

import com.khatsukov.cinemaroomrestservice.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * todo Document type TicketRepository
 */

@Repository
public class TicketRepository {
    private Set<Ticket> ticketsSold = new HashSet<>();

    public Set<Ticket> getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(Set<Ticket> ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public Set<Ticket> addSoldTicket(Ticket ticket) {
        ticketsSold.add(ticket);
        return ticketsSold;
    }

    public boolean removeTicket(UUID token) {
        Optional<Ticket> ticketOptional = findTicketByToken(token);
        if (ticketOptional.isPresent()) {
            ticketsSold.remove(ticketOptional.get());
            return true;
        }
        return false;
    }

    public boolean removeTicket(Ticket ticket) {
        return ticketsSold.remove(ticket);
    }

    public int getCurrentIncome() {
        int income = 0;
        for (Ticket ticket : ticketsSold) {
            income += ticket.getSeat().getPrice();
        }
        return income;
    }

    public Optional<Ticket> findTicketByToken(UUID token) {
        return ticketsSold.stream().filter(ticket -> Objects.equals(token, ticket.getToken())).findFirst();
    }
}
