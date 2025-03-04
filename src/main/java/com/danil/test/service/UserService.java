package com.danil.test.service;

import com.danil.test.model.SeatPreference;
import com.danil.test.model.User;
import com.danil.test.repository.SeatPreferenceRepository;
import com.danil.test.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SeatPreferenceRepository seatPreferenceRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Transactional
    public SeatPreference updateSeatPreference(Long userId, SeatPreference preferenceDetails) {
        User user = getUserById(userId);

        SeatPreference preference = seatPreferenceRepository.findByUser(user).orElse(new SeatPreference());

        preference.setUser(user);
        preference.setPreferWindow(preference.isPreferWindow());
        preference.setPreferExtraLegroom(preference.isPreferExtraLegroom());
        preference.setPreferNearExit(preference.isPreferNearExit());
        preference.setPreferSeatsTogether(preference.isPreferSeatsTogether());

        return seatPreferenceRepository.save(preference);
    }

    public SeatPreference getSeatPreference(Long userId) {
        User user = getUserById(userId);
        return seatPreferenceRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Seat preferences not found for user id " + userId));
    }

}
