package com.danil.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="seats")
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seatId;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String seatClass;

    @Column(nullable = false)
    private String seatType;

    private boolean isWindow;

    private boolean isAisle;

    private boolean hasExtraLegroom;

    private boolean nearExit;

    private BigDecimal pricePremium;

    @OneToMany(mappedBy = "seat")
    @JsonIgnore
    private List<SeatBooking> seatBookings = new ArrayList<>();
}
