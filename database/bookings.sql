-- Drop existing foreign key constraint if it exists
IF EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'FK_Bookings_Users')
BEGIN
    ALTER TABLE Bookings DROP CONSTRAINT FK_Bookings_Users;
    PRINT 'Dropped FK_Bookings_Users constraint.';
END
GO

-- Drop the existing Bookings table
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'Bookings')
BEGIN
    DROP TABLE Bookings;
    PRINT 'Dropped existing Bookings table.';
END
GO

-- Create the Bookings table with INT user_id (matching Users table)
CREATE TABLE Bookings (
    booking_id          INT IDENTITY(1,1) PRIMARY KEY,
    hotel_name          NVARCHAR(200)   NOT NULL,
    room_name           NVARCHAR(200)   NOT NULL,
    destination_country NVARCHAR(100)   NULL,
    city                NVARCHAR(100)   NULL,
    check_in_date       NVARCHAR(50)    NULL,
    check_out_date      NVARCHAR(50)    NULL,
    guests              NVARCHAR(50)    NULL,
    room_type_selection NVARCHAR(50)    NULL,
    nights              INT             NOT NULL DEFAULT 1,
    total_price         FLOAT           NOT NULL DEFAULT 0.0,
    status              NVARCHAR(50)    NOT NULL DEFAULT 'Confirmed',
    booking_date        DATETIME2       NULL,
    user_id             INT          NULL
);
PRINT 'Table [Bookings] created successfully.';
GO

-- Add the foreign key constraint
ALTER TABLE Bookings
ADD CONSTRAINT FK_Bookings_Users 
FOREIGN KEY (user_id) REFERENCES Users(user_id);
PRINT 'Foreign key constraint added successfully.';
GO
-- =============================================
-- Hotels & Suites Database Setup Script
-- =============================================

-- 1. Create the Hotels/Suites Table
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Hotels')
BEGIN
    CREATE TABLE Hotels (
        hotel_id           INT IDENTITY(1,1) PRIMARY KEY,
        name               NVARCHAR(200)   NOT NULL,
        destination_country NVARCHAR(100)   NOT NULL,
        city               NVARCHAR(100)   NOT NULL,
        star_rating        INT             NOT NULL,
        property_type      NVARCHAR(50)    NOT NULL -- 'Hotel' or 'Suite'
    );
    PRINT 'Table [Hotels] created successfully.';
END
ELSE
BEGIN
    PRINT 'Table [Hotels] already exists.';
END
GO

-- 2. Create the Rooms Table (Linked to Hotels/Suites)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'HotelRooms')
BEGIN
    CREATE TABLE HotelRooms (
        room_id           INT IDENTITY(1,1) PRIMARY KEY,
        hotel_id          INT             NOT NULL,
        room_name         NVARCHAR(200)   NOT NULL,
        price_per_night   FLOAT           NOT NULL,
        description       NVARCHAR(500)   NULL,
        CONSTRAINT FK_HotelRooms_Hotels FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id) ON DELETE CASCADE
    );
    PRINT 'Table [HotelRooms] created successfully.';
END
ELSE
BEGIN
    PRINT 'Table [HotelRooms] already exists.';
END
GO

-- =============================================
-- Insert Default Hotel Catalogue Data
-- =============================================

-- Hotel 1
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Apparthotel Sonnenhof')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Apparthotel Sonnenhof', 'Germany', 'Dresden', 4, 'Hotel');
    
    DECLARE @HotelID1 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@HotelID1, 'Standard Room', 3500.0, 'Comfortable room with Wi-Fi, AC and city view.'),
    (@HotelID1, 'Deluxe Room', 5500.0, 'Spacious room with premium furnishings and breakfast included.'),
    (@HotelID1, 'Junior Suite', 8200.0, 'Elegant suite with separate lounge and panoramic windows.');
END

-- Hotel 2
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Hilton Plaza Hotel')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Hilton Plaza Hotel', 'Egypt', 'Cairo', 5, 'Hotel');
    
    DECLARE @HotelID2 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@HotelID2, 'Standard Room', 4200.0, 'Classic Hilton comfort with all modern amenities.'),
    (@HotelID2, 'Deluxe Room', 6800.0, 'Premium bedding, Nile view and complimentary minibar.'),
    (@HotelID2, 'Executive Suite', 12000.0, 'Full executive lounge access, butler service and jacuzzi.');
END

-- Hotel 3
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Steigenberger Pyramids')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Steigenberger Pyramids', 'Egypt', 'Cairo', 5, 'Hotel');
    
    DECLARE @HotelID3 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@HotelID3, 'Standard Room', 3800.0, 'Stylish room with pyramid-inspired décor and garden view.'),
    (@HotelID3, 'Deluxe Room', 6200.0, 'Upgraded furnishings, direct pyramid view and spa access.'),
    (@HotelID3, 'Presidential Suite', 15000.0, 'The ultimate luxury experience with panoramic pyramid panorama.');
END

-- Hotel 4
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Pexels Boutique Resort')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Pexels Boutique Resort', 'Indonesia', 'Bali', 5, 'Hotel');
    
    DECLARE @HotelID4 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@HotelID4, 'Garden Villa', 4500.0, 'Private villa surrounded by lush tropical gardens.'),
    (@HotelID4, 'Ocean Bungalow', 7500.0, 'Overwater bungalow with direct ocean access and sunset views.'),
    (@HotelID4, 'Royal Suite', 13500.0, 'Exclusive suite with private pool, butler and beach access.');
END

-- =============================================
-- Insert Default Suites Catalogue Data
-- =============================================

-- Suite 1
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Luxury Suites Collection')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Luxury Suites Collection', 'UAE', 'Dubai', 5, 'Suite');
    
    DECLARE @SuiteID1 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@SuiteID1, 'Ocean View Suite', 8500.0, 'Breathtaking ocean panorama, king bed, private terrace.');
END

-- Suite 2
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Grand Executive Towers')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Grand Executive Towers', 'France', 'Paris', 5, 'Suite');
    
    DECLARE @SuiteID2 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@SuiteID2, 'Executive Suite', 7200.0, 'Contemporary décor, city skyline view and lounge access.');
END

-- Suite 3
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Skyline Residences')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Skyline Residences', 'UAE', 'Dubai', 5, 'Suite');
    
    DECLARE @SuiteID3 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@SuiteID3, 'Panoramic Skyline Suite', 12500.0, '360° skyline view, jacuzzi, butler and VIP amenities.');
END

-- Suite 4
IF NOT EXISTS (SELECT 1 FROM Hotels WHERE name = 'Garden Palace Hotel')
BEGIN
    INSERT INTO Hotels (name, destination_country, city, star_rating, property_type)
    VALUES ('Garden Palace Hotel', 'Spain', 'Barcelona', 5, 'Suite');
    
    DECLARE @SuiteID4 INT = SCOPE_IDENTITY();
    
    INSERT INTO HotelRooms (hotel_id, room_name, price_per_night, description) VALUES
    (@SuiteID4, 'Grand Balcony Suite', 9800.0, 'Wrap-around balcony, dining area and premium spa access.');
END

PRINT 'Hotels and Suites Catalogues initialized successfully.';
GO
