package com.danil.test.repository;

import com.danil.test.model.Booking;
import com.danil.test.model.Flight;
import com.danil.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByFlight(Flight flight);
}
