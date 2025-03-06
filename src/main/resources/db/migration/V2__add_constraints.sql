-- Add foreign key constraints to seats table
ALTER TABLE seats
    ADD CONSTRAINT fk_seats_aircraft
        FOREIGN KEY (aircraft_id)
            REFERENCES aircraft (aircraft_id);

-- Add foreign key constraints to seat_preferences table
ALTER TABLE seat_preferences
    ADD CONSTRAINT fk_seat_preferences_user
        FOREIGN KEY (user_id)
            REFERENCES users (user_id)
            ON DELETE CASCADE;

-- Add unique constraint to seat_preferences
ALTER TABLE seat_preferences
    ADD CONSTRAINT uq_seat_preferences_user
        UNIQUE (user_id);

-- Add foreign key constraints to flights table
ALTER TABLE flights
    ADD CONSTRAINT fk_flights_departure_airport
        FOREIGN KEY (departure_airport_id)
            REFERENCES airports (airport_id);

ALTER TABLE flights
    ADD CONSTRAINT fk_flights_arrival_airport
        FOREIGN KEY (arrival_airport_id)
            REFERENCES airports (airport_id);

ALTER TABLE flights
    ADD CONSTRAINT fk_flights_aircraft
        FOREIGN KEY (aircraft_id)
            REFERENCES aircraft (aircraft_id);

-- Add check constraint to ensure departure and arrival airports are different
ALTER TABLE flights
    ADD CONSTRAINT chk_different_airports
        CHECK (departure_airport_id != arrival_airport_id);

-- Add check constraint to ensure departure time is before arrival time
ALTER TABLE flights
    ADD CONSTRAINT chk_departure_before_arrival
        CHECK (departure_time < arrival_time);

-- Add foreign key constraints to bookings table
ALTER TABLE bookings
    ADD CONSTRAINT fk_bookings_user
        FOREIGN KEY (user_id)
            REFERENCES users (user_id);

ALTER TABLE bookings
    ADD CONSTRAINT fk_bookings_flight
        FOREIGN KEY (flight_id)
            REFERENCES flights (flight_id);

-- Add foreign key constraints to passengers table
ALTER TABLE passengers
    ADD CONSTRAINT fk_passengers_booking
        FOREIGN KEY (booking_id)
            REFERENCES bookings (booking_id)
            ON DELETE CASCADE;

-- Add foreign key constraints to seat_bookings table
ALTER TABLE seat_bookings
    ADD CONSTRAINT fk_seat_bookings_booking
        FOREIGN KEY (booking_id)
            REFERENCES bookings (booking_id)
            ON DELETE CASCADE;

ALTER TABLE seat_bookings
    ADD CONSTRAINT fk_seat_bookings_seat
        FOREIGN KEY (seat_id)
            REFERENCES seats (seat_id);

ALTER TABLE seat_bookings
    ADD CONSTRAINT fk_seat_bookings_passenger
        FOREIGN KEY (passenger_id)
            REFERENCES passengers (passenger_id)
            ON DELETE CASCADE;

-- Add unique constraint to prevent double booking of seats on the same flight
ALTER TABLE seat_bookings
    ADD CONSTRAINT uq_seat_booking_flight
        UNIQUE (seat_id, booking_id);