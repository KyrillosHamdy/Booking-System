package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.models.*;

public class HotelTest {

    @Test
    public void testHotelConstructorAndGetters() {
        Hotel hotel = new Hotel("Hilton", "Egypt", "Cairo", 5);
        
        assertEquals("Hilton", hotel.getName());
        assertEquals("Egypt", hotel.getDestinationCountry());
        assertEquals("Cairo", hotel.getCity());
        assertEquals(5, hotel.getStarRating());
        assertTrue(hotel.getRooms().isEmpty());
    }

    @Test
    public void testAddRoom() {
        Hotel hotel = new Hotel("Hilton", "Egypt", "Cairo", 5);
        HotelRoom room = new HotelRoom("Standard Room", 3500.0, "Comfortable room");
        
        hotel.addRoom(room);
        
        assertEquals(1, hotel.getRooms().size());
        assertEquals("Standard Room", hotel.getRooms().get(0).getRoomName());
        assertEquals(hotel, room.getHotel()); // Test bidirectional mapping
    }

    @Test
    public void testFindRoom() {
        Hotel hotel = new Hotel("Hilton", "Egypt", "Cairo", 5);
        hotel.addRoom(new HotelRoom("Standard Room", 3500.0, "Comfortable room"));
        hotel.addRoom(new HotelRoom("Deluxe Room", 5500.0, "Spacious room"));
        
        HotelRoom found = hotel.findRoom("deluxe ROOM");
        assertNotNull(found);
        assertEquals("Deluxe Room", found.getRoomName());
        
        HotelRoom notFound = hotel.findRoom("Presidential Suite");
        assertNull(notFound);
    }

    @Test
    public void testGetStartingPrice() {
        Hotel hotel = new Hotel("Hilton", "Egypt", "Cairo", 5);
        assertEquals(0, hotel.getStartingPrice(), "Starting price should be 0 for hotel with no rooms");
        
        hotel.addRoom(new HotelRoom("Deluxe Room", 5500.0, "Spacious room"));
        hotel.addRoom(new HotelRoom("Standard Room", 3500.0, "Comfortable room"));
        hotel.addRoom(new HotelRoom("Suite", 8000.0, "Luxury"));
        
        assertEquals(3500.0, hotel.getStartingPrice(), "Starting price should be the minimum room price");
    }

    @Test
    public void testGetHotelCatalogue() {
        Hotel[] catalogue = Hotel.getHotelCatalogue();
        assertNotNull(catalogue);
        assertEquals(4, catalogue.length, "Hotel catalogue should contain 4 hotels");
        assertFalse(catalogue[0].getRooms().isEmpty(), "Hotels in catalogue should have rooms");
    }

    @Test
    public void testGetSuiteCatalogue() {
        Hotel[] catalogue = Hotel.getSuiteCatalogue();
        assertNotNull(catalogue);
        assertEquals(4, catalogue.length, "Suite catalogue should contain 4 suites");
        assertEquals(1, catalogue[0].getRooms().size(), "Suites in catalogue should have exactly 1 room");
    }
}
