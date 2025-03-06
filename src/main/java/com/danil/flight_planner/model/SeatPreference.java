package com.danil.flight_planner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long preferenceId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private boolean preferWindow;
    private boolean preferNearExit;
    private boolean preferExtraLegroom;
    private boolean preferSeatsTogether;
    // TODO: implement business seats booking
    // private boolean preferBusiness;
}
