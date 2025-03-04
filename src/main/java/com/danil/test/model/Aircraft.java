package com.danil.test.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aircraft")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aircraftId;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String registration;

    @Positive
    private int totalSeats;

    @Positive
    private int rows;

    @Positive
    private int columns;

    private String configuration;

    @OneToMany(mappedBy = "aircraft")
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Seat> seats = new ArrayList<>();

}