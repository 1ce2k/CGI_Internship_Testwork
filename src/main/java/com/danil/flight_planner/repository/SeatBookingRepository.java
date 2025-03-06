package com.danil.flight_planner.repository;

import com.danil.flight_planner.model.Booking;
import com.danil.flight_planner.model.Passenger;
import com.danil.flight_planner.model.Seat;
import com.danil.flight_planner.model.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {
    List<SeatBooking> findByBooking(Booking booking);
    List<SeatBooking> findByPassenger(Passenger passenger);
    List<SeatBooking> findBySeat(Seat seat);
    @Query("SELECT sb FROM SeatBooking sb WHERE sb.booking.flight.flightId = ?1")
    List<SeatBooking> findByFlightId(Long flightId);
}
