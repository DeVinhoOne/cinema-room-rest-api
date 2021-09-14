package com.example.cinemaroomrestservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();

    @GetMapping("/cinema")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        UUID token = UUID.randomUUID();
        try {
            return ResponseEntity.ok(cinema.bookTicket(token, seat));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody String token) {
        try {
            return ResponseEntity.ok(Map.of("returned_ticket", cinema.returnTicket(token)));
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
        return ResponseEntity.ok(cinema.getStats());
    }
}
