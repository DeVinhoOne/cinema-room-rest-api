package com.example.cinemaroomrestservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinema() {
        return this.cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        String token = UUID.randomUUID().toString();
        try {
            this.cinema.bookTicket(token, seat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
        //return last ticket
        return ResponseEntity.ok(this.cinema.getPurchasedTickets().get(this.cinema.getPurchasedTickets().size() - 1));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody String token) {
        try {
            return ResponseEntity.ok(Map.of("returned_ticket", this.cinema.returnTicket(token)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "The password is wrong!"));
        }
        return ResponseEntity.ok(this.cinema.getStats());
    }
}
