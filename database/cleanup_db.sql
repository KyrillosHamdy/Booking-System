-- Drop all tables to clean up the database

-- Drop foreign key constraints first
IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'FK_Bookings_Hotels')
    ALTER TABLE Bookings DROP CONSTRAINT FK_Bookings_Hotels;
IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'FK_Bookings_Users')
    ALTER TABLE Bookings DROP CONSTRAINT FK_Bookings_Users;
IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'FK_Payments_Users')
    ALTER TABLE Payments DROP CONSTRAINT FK_Payments_Users;
IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'FK_HotelRooms_Hotels')
    ALTER TABLE HotelRooms DROP CONSTRAINT FK_HotelRooms_Hotels;

-- Drop tables
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'Bookings')
    DROP TABLE Bookings;
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'Payments')
    DROP TABLE Payments;
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'Feedback')
    DROP TABLE Feedback;
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'HotelRooms')
    DROP TABLE HotelRooms;
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'Hotels')
    DROP TABLE Hotels;
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'Users')
    DROP TABLE Users;

PRINT 'All tables dropped successfully.';
