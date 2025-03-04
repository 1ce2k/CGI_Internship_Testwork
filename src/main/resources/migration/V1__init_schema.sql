-- Create airports table
CREATE TABLE airports (
                          airport_id BIGSERIAL PRIMARY KEY,
                          code VARCHAR(3) NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          city VARCHAR(255) NOT NULL,
                          country VARCHAR(255) NOT NULL
);

-- Create aircraft table
CREATE TABLE aircraft (
                          aircraft_id BIGSERIAL PRIMARY KEY,
                          model VARCHAR(255) NOT NULL,
                          registration VARCHAR(255) NOT NULL UNIQUE,
                          total_seats INTEGER NOT NULL,
                          rows INTEGER NOT NULL,
                          columns INTEGER NOT NULL,
                          configuration VARCHAR(50)
);

-- Create seats table
CREATE TABLE seats (
                       seat_id BIGSERIAL PRIMARY KEY,
                       aircraft_id BIGSERIAL NOT NULL,
                       seat_number VARCHAR(10) NOT NULL,
                       seat_class VARCHAR(50) NOT NULL,
                       seat_type VARCHAR(50) NOT NULL,
                       is_window BOOLEAN NOT NULL DEFAULT FALSE,
                       is_aisle BOOLEAN NOT NULL DEFAULT FALSE,
                       has_extra_legroom BOOLEAN NOT NULL DEFAULT FALSE,
                       near_exit BOOLEAN NOT NULL DEFAULT FALSE,
                       price_premium DECIMAL(10, 2)
);

-- Create users table
CREATE TABLE users (
                       user_id BIGSERIAL PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone VARCHAR(50)
);

-- Create seat_preferences table
CREATE TABLE seat_preferences (
                                  preference_id BIGSERIAL PRIMARY KEY,
                                  user_id BIGSERIAL NOT NULL,
                                  prefer_window BOOLEAN NOT NULL DEFAULT FALSE,
                                  prefer_extra_legroom BOOLEAN NOT NULL DEFAULT FALSE,
                                  prefer_near_exit BOOLEAN NOT NULL DEFAULT FALSE,
                                  prefer_seats_together BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create flights table
CREATE TABLE flights (
                         flight_id BIGSERIAL PRIMARY KEY,
                         flight_number VARCHAR(20) NOT NULL,
                         departure_airport_id BIGSERIAL NOT NULL,
                         arrival_airport_id BIGSERIAL NOT NULL,
                         departure_time TIMESTAMP NOT NULL,
                         arrival_time TIMESTAMP NOT NULL,
                         base_price DECIMAL(10, 2) NOT NULL,
                         aircraft_id BIGSERIAL NOT NULL,
                         status VARCHAR(50) NOT NULL
);

-- Create bookings table
CREATE TABLE bookings (
                          booking_id BIGSERIAL PRIMARY KEY,
                          user_id BIGSERIAL NOT NULL,
                          flight_id BIGSERIAL NOT NULL,
                          booking_time TIMESTAMP NOT NULL,
                          total_price DECIMAL(10, 2) NOT NULL,
                          status VARCHAR(50) NOT NULL
);

-- Create passengers table
CREATE TABLE passengers (
                            passenger_id BIGSERIAL PRIMARY KEY,
                            booking_id BIGSERIAL NOT NULL,
                            first_name VARCHAR(255) NOT NULL,
                            last_name VARCHAR(255) NOT NULL,
                            document_type VARCHAR(50),
                            document_number VARCHAR(50)
);

-- Create seat_bookings table
CREATE TABLE seat_bookings (
                               seat_booking_id BIGSERIAL PRIMARY KEY,
                               booking_id BIGSERIAL NOT NULL,
                               seat_id BIGSERIAL NOT NULL,
                               passenger_id BIGSERIAL NOT NULL
);