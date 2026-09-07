package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.models.*;

public class HotelRoomTest {

    @Test
    public void testHotelRoomConstructorAndGetters() {
        HotelRoom room = new HotelRoom("Standard Room", 3500.0, "Comfortable room");
        
        assertEquals("Standard Room", room.getRoomName());
        assertEquals(3500.0, room.getPricePerNight());
        assertEquals("Comfortable room", room.getDescription());
    }

    @Test
    public void testSetters() {
        HotelRoom room = new HotelRoom();
        room.setRoomName("Deluxe Room");
        room.setPricePerNight(5500.0);
        room.setDescription("Spacious room");

        assertEquals("Deluxe Room", room.getRoomName());
        assertEquals(5500.0, room.getPricePerNight());
        assertEquals("Spacious room", room.getDescription());
    }

    @Test
    public void testToString() {
        HotelRoom room = new HotelRoom("Standard Room", 3500.0, "Comfortable room");
        String expected = "Standard Room \u2013 EGP 3,500/night";
        assertEquals(expected, room.toString());
    }
}
