package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.models.*;
import java.time.LocalDateTime;

public class BookingTest {

    @Test
    public void testBookingSettersAndGetters() {
        Booking booking = new Booking();
        User user = new User();
        
        booking.setUser(user);
        booking.setHotelName("Hilton");
        booking.setRoomName("Standard Room");
        booking.setRoomTypeSelection("Standard Room");
        booking.setDestinationCountry("Egypt");
        booking.setCity("Cairo");
        booking.setCheckInDate("10 May 2026");
        booking.setCheckOutDate("15 May 2026");
        booking.setGuests("2");
        booking.setNights(5);
        booking.setTotalPrice(15000.0);
        booking.setStatus("Confirmed");
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(now);

        assertEquals(user, booking.getUser());
        assertEquals("Hilton", booking.getHotelName());
        assertEquals("Standard Room", booking.getRoomName());
        assertEquals("Standard Room", booking.getRoomTypeSelection());
        assertEquals("Egypt", booking.getDestinationCountry());
        assertEquals("Cairo", booking.getCity());
        assertEquals("10 May 2026", booking.getCheckInDate());
        assertEquals("15 May 2026", booking.getCheckOutDate());
        assertEquals("2", booking.getGuests());
        assertEquals(5, booking.getNights());
        assertEquals(15000.0, booking.getTotalPrice());
        assertEquals("Confirmed", booking.getStatus());
        assertEquals(now, booking.getBookingDate());
    }
}
