package com.danil.flight_planner.controllers;

import com.danil.flight_planner.model.Airport;
import com.danil.flight_planner.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Airport> getAirportByCode(@PathVariable String code) {
        return ResponseEntity.ok(airportService.getAirportByCode(code));
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportService.createAirport(airport), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return ResponseEntity.ok(airportService.updateAirport(id, airport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
