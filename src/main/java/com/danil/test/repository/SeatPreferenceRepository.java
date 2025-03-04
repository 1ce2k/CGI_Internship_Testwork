package com.danil.test.repository;

import com.danil.test.model.SeatPreference;
import com.danil.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatPreferenceRepository extends JpaRepository<SeatPreference, Long> {
    Optional<SeatPreference> findByUser(User user);
}