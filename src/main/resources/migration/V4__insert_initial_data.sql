-- Insert airports
INSERT INTO airports (code, name, city, country) VALUES
                                                     ('TLL', 'Lennart Meri Tallinn Airport', 'Tallinn', 'Estonia'),
                                                     ('ARN', 'Stockholm Arlanda Airport', 'Stockholm', 'Sweden'),
                                                     ('HEL', 'Helsinki Airport', 'Helsinki', 'Finland'),
                                                     ('RIX', 'Riga International Airport', 'Riga', 'Latvia'),
                                                     ('CPH', 'Copenhagen Airport', 'Copenhagen', 'Denmark');

-- Insert aircraft
INSERT INTO aircraft (model, registration, total_seats, rows, columns, configuration) VALUES
                                                                                          ('Airbus A320', 'ES-ABC', 180, 30, 6, '3-3'),
                                                                                          ('Boeing 737-800', 'ES-DEF', 189, 32, 6, '3-3'),
                                                                                          ('Bombardier CRJ900', 'ES-GHI', 90, 20, 5, '2-3');

-- Insert seats for Airbus A320 (simplified - just a few rows as examples)
-- Business class (rows 1-2)
INSERT INTO seats (aircraft_id, seat_number, seat_class, seat_type, is_window, is_aisle, has_extra_legroom, near_exit, price_premium) VALUES
-- Row 1
(1, '1A', 'BUSINESS', 'WINDOW', TRUE, FALSE, TRUE, TRUE, 50.00),
(1, '1B', 'BUSINESS', 'AISLE', FALSE, TRUE, TRUE, TRUE, 45.00),
(1, '1C', 'BUSINESS', 'AISLE', FALSE, TRUE, TRUE, TRUE, 45.00),
(1, '1D', 'BUSINESS', 'WINDOW', TRUE, FALSE, TRUE, TRUE, 50.00),
-- Row 2
(1, '2A', 'BUSINESS', 'WINDOW', TRUE, FALSE, TRUE, FALSE, 45.00),
(1, '2B', 'BUSINESS', 'AISLE', FALSE, TRUE, TRUE, FALSE, 40.00),
(1, '2C', 'BUSINESS', 'AISLE', FALSE, TRUE, TRUE, FALSE, 40.00),
(1, '2D', 'BUSINESS', 'WINDOW', TRUE, FALSE, TRUE, FALSE, 45.00);

-- Economy class (just a few rows as examples)
INSERT INTO seats (aircraft_id, seat_number, seat_class, seat_type, is_window, is_aisle, has_extra_legroom, near_exit, price_premium) VALUES
-- Row 10 (emergency exit row)
(1, '10A', 'ECONOMY', 'WINDOW', TRUE, FALSE, TRUE, TRUE, 25.00),
(1, '10B', 'ECONOMY', 'MIDDLE', FALSE, FALSE, TRUE, TRUE, 20.00),
(1, '10C', 'ECONOMY', 'AISLE', FALSE, TRUE, TRUE, TRUE, 22.00),
(1, '10D', 'ECONOMY', 'AISLE', FALSE, TRUE, TRUE, TRUE, 22.00),
(1, '10E', 'ECONOMY', 'MIDDLE', FALSE, FALSE, TRUE, TRUE, 20.00),
(1, '10F', 'ECONOMY', 'WINDOW', TRUE, FALSE, TRUE, TRUE, 25.00),
-- Row 11
(1, '11A', 'ECONOMY', 'WINDOW', TRUE, FALSE, FALSE, FALSE, 15.00),
(1, '11B', 'ECONOMY', 'MIDDLE', FALSE, FALSE, FALSE, FALSE, 0.00),
(1, '11C', 'ECONOMY', 'AISLE', FALSE, TRUE, FALSE, FALSE, 10.00),
(1, '11D', 'ECONOMY', 'AISLE', FALSE, TRUE, FALSE, FALSE, 10.00),
(1, '11E', 'ECONOMY', 'MIDDLE', FALSE, FALSE, FALSE, FALSE, 0.00),
(1, '11F', 'ECONOMY', 'WINDOW', TRUE, FALSE, FALSE, FALSE, 15.00),
-- Row 12
(1, '12A', 'ECONOMY', 'WINDOW', TRUE, FALSE, FALSE, FALSE, 15.00),
(1, '12B', 'ECONOMY', 'MIDDLE', FALSE, FALSE, FALSE, FALSE, 0.00),
(1, '12C', 'ECONOMY', 'AISLE', FALSE, TRUE, FALSE, FALSE, 10.00),
(1, '12D', 'ECONOMY', 'AISLE', FALSE, TRUE, FALSE, FALSE, 10.00),
(1, '12E', 'ECONOMY', 'MIDDLE', FALSE, FALSE, FALSE, FALSE, 0.00),
(1, '12F', 'ECONOMY', 'WINDOW', TRUE, FALSE, FALSE, FALSE, 15.00);

-- Insert users
INSERT INTO users (first_name, last_name, email, phone) VALUES
                                                            ('John', 'Doe', 'john.doe@example.com', '+372 5555 1234'),
                                                            ('Jane', 'Smith', 'jane.smith@example.com', '+372 5555 5678'),
                                                            ('Bob', 'Johnson', 'bob.johnson@example.com', '+372 5555 9012');

-- Insert seat preferences
INSERT INTO seat_preferences (user_id, prefer_window, prefer_extra_legroom, prefer_near_exit, prefer_seats_together) VALUES
                                                                                                                         (1, TRUE, FALSE, TRUE, TRUE),
                                                                                                                         (2, FALSE, TRUE, FALSE, TRUE),
                                                                                                                         (3, TRUE, TRUE, FALSE, FALSE);

-- Insert flights
INSERT INTO flights (flight_number, departure_airport_id, arrival_airport_id, departure_time, arrival_time, base_price, aircraft_id, status) VALUES
                                                                                                                                                 ('ES9012', 1, 2, NOW() + INTERVAL '1 day' + INTERVAL '16 hours' + INTERVAL '20 minutes', NOW() + INTERVAL '1 day' + INTERVAL '18 hours' + INTERVAL '25 minutes', 129.00, 1, 'SCHEDULED'),
                                                                                                                                                 ('ES9013', 1, 3, NOW() + INTERVAL '1 day' + INTERVAL '8 hours' + INTERVAL '45 minutes', NOW() + INTERVAL '1 day' + INTERVAL '9 hours' + INTERVAL '30 minutes', 89.00, 1, 'SCHEDULED'),
                                                                                                                                                 ('ES9014', 1, 4, NOW() + INTERVAL '2 days' + INTERVAL '10 hours' + INTERVAL '15 minutes', NOW() + INTERVAL '2 days' + INTERVAL '11 hours' + INTERVAL '30 minutes', 99.00, 2, 'SCHEDULED'),
                                                                                                                                                 ('ES9015', 2, 1, NOW() + INTERVAL '1 day' + INTERVAL '20 hours' + INTERVAL '30 minutes', NOW() + INTERVAL '1 day' + INTERVAL '22 hours' + INTERVAL '35 minutes', 139.00, 1, 'SCHEDULED');

-- Insert a sample booking
INSERT INTO bookings (user_id, flight_id, booking_time, total_price, status) VALUES
    (1, 1, NOW() - INTERVAL '2 days', 159.00, 'CONFIRMED');

-- Insert passengers for the booking
INSERT INTO passengers (booking_id, first_name, last_name, document_type, document_number) VALUES
    (1, 'John', 'Doe', 'PASSPORT', 'AB123456');

-- Insert seat booking
INSERT INTO seat_bookings (booking_id, seat_id, passenger_id) VALUES
    (1, 13, 1); -- Assuming seat_id 13 is 11A