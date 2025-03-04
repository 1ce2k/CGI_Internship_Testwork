package com.danil.test.service;

import com.danil.test.model.Aircraft;
import com.danil.test.repository.AircraftRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id).orElseThrow(() -> new RuntimeException("Aircraft not found with id: " + id));
    }

    @Transactional
    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Transactional
    public Aircraft updateAircraft(Long id, Aircraft aircraftDetails) {
        Aircraft aircraft = getAircraftById(id);

        aircraft.setModel(aircraftDetails.getModel());
        aircraft.setRegistration(aircraftDetails.getRegistration());
        aircraft.setTotalSeats(aircraftDetails.getTotalSeats());
        aircraft.setRows(aircraftDetails.getRows());
        aircraft.setColumns(aircraftDetails.getColumns());
        aircraft.setConfiguration(aircraftDetails.getConfiguration());

        return aircraftRepository.save(aircraft);
    }

    @Transactional
    public void deleteAircraft(Long id) {
        Aircraft aircraft = getAircraftById(id);
        aircraftRepository.delete(aircraft);
    }
}
