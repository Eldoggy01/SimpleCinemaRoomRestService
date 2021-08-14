package com.khatsukov.cinemaroomrestservice.api;

import com.khatsukov.cinemaroomrestservice.data.TicketRepository;
import com.khatsukov.cinemaroomrestservice.domain.CinemaRoom;
import com.khatsukov.cinemaroomrestservice.domain.Seat;
import com.khatsukov.cinemaroomrestservice.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * todo Document type CinemaRoomControler
 */
@RestController
public class CinemaRoomController {

    private CinemaRoom cinemaRoom;
    private TicketRepository ticketRepo;

    @Autowired
    public CinemaRoomController(CinemaRoom cinemaRoom, TicketRepository repo) {
        this.cinemaRoom = cinemaRoom;
        this.ticketRepo = repo;
    }

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody Map<String, Integer> seat) {
        verifyThatSeatCoordinatesInCorrectBounds(seat.get("row"), seat.get("column"));

        Optional<Seat> seatToPurchase = cinemaRoom.getSeat(seat.get("row"), seat.get("column"));
        if (seatToPurchase.isEmpty()) {
            throw new SeatNotAvailableException("Seat doesnt found!");
        } else if (seatToPurchase.get().isPurchased()) {
            throw new SeatNotAvailableException("The ticket has been already purchased!");
        } else {
            seatToPurchase.get().setPurchased(true);
            Ticket ticket = new Ticket(UUID.randomUUID(), seatToPurchase.get());
            ticketRepo.addSoldTicket(ticket);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, Seat>> returnTicket(@RequestBody Map<String, UUID> tokenBody) {
        UUID token = tokenBody.get("token");
        Optional<Ticket> optionalTicket = ticketRepo.findTicketByToken(token);
        if (optionalTicket.isPresent()) {
            ticketRepo.removeTicket(optionalTicket.get());
            Seat seat = optionalTicket.get().getSeat();
            seat.setPurchased(false);
            return new ResponseEntity<>(Map.of("returned_ticket", seat), HttpStatus.OK);
        } else {
            throw new TokenExpiredException("Wrong token!");
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<Map> getStats(@RequestParam(value = "password", required = false) String password) {
        if (Objects.equals("super_secret", password)) {
            Map<String, Integer> bodyMap = new HashMap<>();
            bodyMap.put("current_income", ticketRepo.getCurrentIncome());
            bodyMap.put("number_of_available_seats", cinemaRoom.getAvailableSeatsList().size());
            bodyMap.put("number_of_purchased_tickets", ticketRepo.getTicketsSold().size());
            return new ResponseEntity<>(bodyMap, HttpStatus.OK);
        } else {
            throw new PasswordNotCorrectException("The password is wrong!");
        }
    }

    private void verifyThatSeatCoordinatesInCorrectBounds(int row, int column) {
        if ((row > cinemaRoom.getTotalRows() || row <= 0 || column > cinemaRoom.getTotalColumns() || column <= 0)) {
            throw new SeatNotAvailableException("The number of a row or a column is out of bounds!");
        }
    }
}
