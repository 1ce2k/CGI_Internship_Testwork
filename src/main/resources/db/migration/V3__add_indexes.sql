-- Add indexes for better query performance

-- Airports indexes
CREATE INDEX idx_airports_code ON airports(code);

-- Aircraft indexes
CREATE INDEX idx_aircraft_registration ON aircraft(registration);

-- Seats indexes
CREATE INDEX idx_seats_aircraft_id ON seats(aircraft_id);
CREATE INDEX idx_seats_seat_number ON seats(seat_number);
CREATE INDEX idx_seats_window ON seats(is_window);
CREATE INDEX idx_seats_extra_legroom ON seats(has_extra_legroom);
CREATE INDEX idx_seats_near_exit ON seats(near_exit);

-- Users indexes
CREATE INDEX idx_users_email ON users(email);

-- Flights indexes
CREATE INDEX idx_flights_flight_number ON flights(flight_number);
CREATE INDEX idx_flights_departure_airport ON flights(departure_airport_id);
CREATE INDEX idx_flights_arrival_airport ON flights(arrival_airport_id);
CREATE INDEX idx_flights_departure_time ON flights(departure_time);
CREATE INDEX idx_flights_aircraft ON flights(aircraft_id);
CREATE INDEX idx_flights_status ON flights(status);

-- Bookings indexes
CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_flight ON bookings(flight_id);
CREATE INDEX idx_bookings_status ON bookings(status);

-- Passengers indexes
CREATE INDEX idx_passengers_booking ON passengers(booking_id);
CREATE INDEX idx_passengers_document ON passengers(document_type, document_number);

-- Seat bookings indexes
CREATE INDEX idx_seat_bookings_booking ON seat_bookings(booking_id);
CREATE INDEX idx_seat_bookings_seat ON seat_bookings(seat_id);
CREATE INDEX idx_seat_bookings_passenger ON seat_bookings(passenger_id);