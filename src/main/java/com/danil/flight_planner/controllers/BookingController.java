package com.danil.flight_planner.controllers;

import com.danil.flight_planner.model.Booking;
import com.danil.flight_planner.model.Passenger;
import com.danil.flight_planner.service.BookingService;
import com.danil.flight_planner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(id));
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long userId,
            @RequestParam Long flightId,
            @RequestParam List<Long> seats,
            @RequestBody List<Passenger> passengers
    ) {
        return new ResponseEntity<>(bookingService.createBooking(userId, flightId, seats, passengers), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Booking> updateBookingStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        if (status == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }
}