package com.danil.flight_planner.controllers;

import com.danil.flight_planner.model.SeatPreference;
import com.danil.flight_planner.model.User;
import com.danil.flight_planner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/preferences")
    public ResponseEntity<SeatPreference> getSeatPreferences(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getSeatPreference(id));
    }

    @PutMapping("/{id}/preferences")
    public ResponseEntity<SeatPreference> updateSeatPreference(
            @PathVariable("id") long id,
            @RequestBody SeatPreference preference) {
        return ResponseEntity.ok(userService.updateSeatPreference(id, preference));
    }

}
