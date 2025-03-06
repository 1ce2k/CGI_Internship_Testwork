package com.danil.flight_planner.repository;

import com.danil.flight_planner.model.Booking;
import com.danil.flight_planner.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByBooking(Booking booking);
}
