-- src/main/resources/db/migration/V0__test_migration.sql
CREATE TABLE flyway_test (
                             id SERIAL PRIMARY KEY,
                             test_column VARCHAR(255)
);

INSERT INTO flyway_test (test_column) VALUES ('Flyway is working!');