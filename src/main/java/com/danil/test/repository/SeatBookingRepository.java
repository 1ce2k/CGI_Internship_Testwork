package com.danil.test.repository;

import com.danil.test.model.Booking;
import com.danil.test.model.Passenger;
import com.danil.test.model.Seat;
import com.danil.test.model.SeatBooking;
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
