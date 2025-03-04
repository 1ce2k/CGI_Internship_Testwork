package com.danil.test.controllers;

import com.danil.test.model.Seat;
import com.danil.test.model.SeatPreference;
import com.danil.test.service.SeatService;
import com.danil.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable long id) {
        return ResponseEntity.ok(seatService.getSeatById(id));
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<Seat>> getRecommendedSeats(
            @RequestParam Long flightId,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "1") int numberOfSeats) {
        SeatPreference preference = userService.getSeatPreference(userId);
        return ResponseEntity.ok(seatService.recommendedSeats(flightId, preference, numberOfSeats));
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        return new ResponseEntity<>(seatService.createSeat(seat),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable long id, @RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.updateSeat(id, seat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seat> deleteSeat(@PathVariable long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }



}
