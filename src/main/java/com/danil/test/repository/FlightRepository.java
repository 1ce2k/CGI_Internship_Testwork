package com.danil.test.repository;

import com.danil.test.model.Airport;
import com.danil.test.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirport(Airport departureAirport);

    List<Flight> findByArrivalAirport(Airport arrivalAirport);
    List<Flight> findByDepartureAirportAndArrivalAirport(Airport departureAirport, Airport arrivalAirport);

    @Query("SELECT f FROM Flight f WHERE f.departureTime >= ?1 AND f.arrivalTime <= ?2")
    List<Flight> findByDepartureTimeBetween(LocalDateTime departureTime, LocalDateTime arrivalTime);

    @Query("SELECT f FROM Flight f WHERE f.basePrice BETWEEN ?1 AND ?2")
    List<Flight> findByBasePriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT f FROM Flight f WHERE " +
            "(:departureAirportId IS NULL OR f.departureAirport.airportId = :departureAirportId) AND " +
            "(:arrivalAirportId IS NULL OR f.arrivalAirport.airportId = :arrivalAirportId) AND " +
            "(:departureDate IS NULL OR CAST(f.departureTime AS DATE) = :departureDate) AND " +
            "(:minPrice IS NULL OR f.basePrice >= :minPrice) AND " +
            "(:maxPrice IS NULL OR f.basePrice >= :maxPrice)")
    List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId,
                               LocalDateTime departureDate ,BigDecimal minPrice, BigDecimal maxPrice);

}
