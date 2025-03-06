package com.danil.flight_planner.repository;

import com.danil.flight_planner.model.SeatPreference;
import com.danil.flight_planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatPreferenceRepository extends JpaRepository<SeatPreference, Long> {
    Optional<SeatPreference> findByUser(User user);
}