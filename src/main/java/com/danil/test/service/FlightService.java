package com.danil.test.service;

import com.danil.test.model.Airport;
import com.danil.test.model.Flight;
import com.danil.test.repository.AirportRepository;
import com.danil.test.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + id));
    }

    public List<Flight> searchFlights(String departureAirportCode, String arrivalAirportCode,
                                      LocalDate departureDate, BigDecimal minPrice, BigDecimal maxPrice) {

        Long departureAirportId = null;
        Long arrivalAirportId = null;
        LocalDateTime departureDateStart = null;

        if (departureAirportCode != null) {
            Airport departureAirport = airportRepository.findByCode(departureAirportCode)
                    .orElseThrow(() -> new EntityNotFoundException("Airport not found with code: " + departureAirportCode));
            departureAirportId = departureAirport.getAirportId();
        }

        if (arrivalAirportCode != null) {
            Airport arrivalAirport = airportRepository.findByCode(arrivalAirportCode)
                    .orElseThrow(() -> new EntityNotFoundException("Airport not found with code: " + arrivalAirportCode));
            arrivalAirportId = arrivalAirport.getAirportId();
        }

        if (departureDate != null) {
            departureDateStart = LocalDateTime.of(departureDate, LocalTime.MIDNIGHT);
        }

        return flightRepository.searchFlights(departureAirportId, arrivalAirportId, departureDateStart, minPrice, maxPrice);
    }

    @Transactional
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = getFlightById(id);

        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureAirport(flightDetails.getDepartureAirport());
        flight.setArrivalAirport(flightDetails.getArrivalAirport());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setBasePrice(flightDetails.getBasePrice());
        flight.setAircraft(flightDetails.getAircraft());
        flight.setStatus(flightDetails.getStatus());
        return flightRepository.save(flight);
    }

    @Transactional
    public void deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

}
