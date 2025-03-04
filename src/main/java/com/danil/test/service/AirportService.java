package com.danil.test.service;

import com.danil.test.model.Airport;
import com.danil.test.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }



    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElseThrow(() -> new RuntimeException("Airport not found with id: " + id));
    }

    public Airport getAirportByCode(String code) {
        return airportRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Airport not found with code: " + code));
    }

    @Transactional
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Transactional
    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport airport = getAirportById(id);
        airport.setCode(airportDetails.getCode());
        airport.setName(airportDetails.getName());
        airport.setCity(airportDetails.getCity());
        airport.setCountry(airportDetails.getCountry());

        return airportRepository.save(airport);
    }

    @Transactional
    public void deleteAirport(Long id) {
        Airport airport = getAirportById(id);
        airportRepository.delete(airport);
    }
}
