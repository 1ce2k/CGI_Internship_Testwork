package com.danil.flight_planner.repository;

import com.danil.flight_planner.model.Aircraft;
import com.danil.flight_planner.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByAircraft(Aircraft aircraft);

    List<Seat> findByAircraftAndIsWindow(Aircraft aircraft, boolean isWindow);
    List<Seat> findByAircraftAndHasExtraLegroom(Aircraft aircraft, boolean hasExtraLegRoom);
    List<Seat> findByAircraftAndNearExit(Aircraft aircraft, boolean nearExit);

    @Query("SELECT s FROM Seat s WHERE s.aircraft = ?1 AND s.seatId NOT IN " +
            "(SELECT sb.seat.seatId FROM SeatBooking sb JOIN sb.booking b WHERE b.flight.aircraft = ?1 AND b.flight.flightId = ?2)")
    List<Seat> findAvailableSeatsForFlight(Aircraft aircraft, Long flightId);

}
