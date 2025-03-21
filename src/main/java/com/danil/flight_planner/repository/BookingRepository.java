package com.danil.flight_planner.repository;

import com.danil.flight_planner.model.Booking;
import com.danil.flight_planner.model.Flight;
import com.danil.flight_planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByFlight(Flight flight);
}
