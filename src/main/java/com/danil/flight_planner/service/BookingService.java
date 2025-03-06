package com.danil.flight_planner.service;

import com.danil.flight_planner.model.*;
import com.danil.flight_planner.repository.BookingRepository;
import com.danil.flight_planner.repository.PassengerRepository;
import com.danil.flight_planner.repository.SeatBookingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final SeatBookingRepository seatBookingRepository;
    private final FlightService flightService;
    private final SeatService seatService;
    private final UserService userService;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
    }

    public List<Booking> getBookingsByUser(Long userId) {
        User user = userService.getUserById(userId);
        return bookingRepository.findByUser(user);
    }

    @Transactional
    public Booking createBooking(Long userId, Long flightId, List<Long> seatIds, List<Passenger> passengers) {
        User user = userService.getUserById(userId);
        Flight flight = flightService.getFlightById(flightId);

        if (seatIds.size() != passengers.size()) {
            throw new IllegalArgumentException("Number of seats must match passengers");
        }

        BigDecimal totalPrice = flight.getBasePrice().multiply(new BigDecimal(passengers.size()));

        for (Long seatId : seatIds) {
            Seat seat = seatService.getSeatById(seatId);
            if (seat.getPricePremium() != null){
                totalPrice = totalPrice.add(seat.getPricePremium());
            }
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalPrice(totalPrice);
        booking.setStatus("CONFIRMED");
        Booking savedBooking = bookingRepository.save(booking);

        for (Passenger passenger : passengers) {
            passenger.setBooking(savedBooking);
            passengerRepository.save(passenger);
        }

        for (int i = 0; i < seatIds.size(); i++) {
            SeatBooking seatBooking = new SeatBooking();
            seatBooking.setBooking(savedBooking);
            seatBooking.setSeat(seatService.getSeatById(seatIds.get(i)));
            seatBooking.setPassenger(passengers.get(i));
            seatBookingRepository.save(seatBooking);
        }
        return savedBooking;
    }

    @Transactional
    public Booking updateBookingStatus(Long id, String status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBookingById(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }

}
