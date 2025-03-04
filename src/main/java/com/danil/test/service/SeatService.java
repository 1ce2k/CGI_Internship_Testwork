package com.danil.test.service;

import com.danil.test.model.Aircraft;
import com.danil.test.model.Flight;
import com.danil.test.model.Seat;
import com.danil.test.model.SeatPreference;
import com.danil.test.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final FlightService flightService;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat Not Found with id: " + id));
    }

    public List<Seat> getSeatsByAircraft(Aircraft aircraft) {
        return seatRepository.findByAircraft(aircraft);
    }

    public List<Seat> getAvailableSeatsForFlight(Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        return seatRepository.findAvailableSeatsForFlight(flight.getAircraft(), flightId);
    }

    public List<Seat> recommendedSeats(Long flightId, SeatPreference preference, int numberOfSeats) {
        List<Seat> availableSeats = getAvailableSeatsForFlight(flightId);
        List<Seat> filteredSeats = availableSeats.stream()
                .filter(seat -> {
                    boolean matchesPreference = true;
                    if (preference.isPreferWindow() && !seat.isWindow()) {
                        matchesPreference = false;
                    }
                    if (preference.isPreferNearExit() && !seat.isNearExit()) {
                        matchesPreference = false;
                    }
                    if (preference.isPreferExtraLegroom() && !seat.isHasExtraLegroom()){
                        matchesPreference = false;
                    }
                    return matchesPreference;
                })
                .collect(Collectors.toList());

        if (filteredSeats.isEmpty()) {
            filteredSeats = availableSeats;
        }

        if (preference.isPreferSeatsTogether() && filteredSeats.size() >= numberOfSeats) {
            return findSeatsNextToEachOther(filteredSeats, numberOfSeats);
        }

        return filteredSeats.stream().limit(numberOfSeats).collect(Collectors.toList());
    }

    private List<Seat> findSeatsNextToEachOther(List<Seat> seats, int numberOfSeats) {
        // Simplified implementation
        seats.sort(Comparator.comparing((Seat s) -> s.getSeatNumber().length()).thenComparing(Seat::getSeatNumber));

        List<Seat> result = new ArrayList<>(seats);
        String currentRow = null;

        for (Seat seat : seats) {
            String seatRow = seat.getSeatNumber().replaceAll("[^0-9]", "");
            if (currentRow == null || !currentRow.equals(seatRow)) {
                currentRow = seatRow;
                result.clear();
                result.add(seat);
            }
            else {
                result.add(seat);
                if (result.size() == numberOfSeats) {
                    return result;
                }
            }
        }
        return seats.stream().limit(numberOfSeats).collect(Collectors.toList());
    }

    @Transactional
    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Transactional
    public Seat updateSeat(Long id, Seat seatDetails) {
        Seat seat = getSeatById(id);

        seat.setAircraft(seatDetails.getAircraft());
        seat.setSeatNumber(seatDetails.getSeatNumber());
        seat.setSeatClass(seatDetails.getSeatClass());
        seat.setSeatType(seatDetails.getSeatType());
        seat.setWindow(seatDetails.isWindow());
        seat.setNearExit(seatDetails.isNearExit());
        seat.setHasExtraLegroom(seatDetails.isHasExtraLegroom());
        seat.setNearExit(seatDetails.isNearExit());
        seat.setPricePremium(seatDetails.getPricePremium());

        return seatRepository.save(seat);
    }

    @Transactional
    public void deleteSeat(Long id) {
        Seat seat = getSeatById(id);
        seatRepository.delete(seat);
    }
}
