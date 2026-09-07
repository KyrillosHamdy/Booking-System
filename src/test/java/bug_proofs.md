# Bug Findings and Proofs

Here are the code snippets proving the three issues you found during testing.

## 1. Room Selection Saves "Standard Room"
**File:** `src/main/java/com/mycompany/Softwarepr1/Homepage.java`

When saving the booking, the code takes the `roomName` directly from the `HotelRoom` object (which is often initialized as "Standard Room" in `Hotel.java`) rather than matching the object to what you selected in the dropdown (`roomSel`).

```java
// In the handleBooking method around line 2768:
booking.setUser(loggedInUser);
booking.setHotelName(hotel.getName());
booking.setRoomName(room.getRoomName());  // <-- BUG: This pulls the default room name (e.g. "Standard Room")
booking.setDestinationCountry(country);
// ...
booking.setRoomTypeSelection(roomSel);    // <-- It sets roomSel here, but room_name is already "Standard Room"
```

## 2. Check-out Date Allowed Before Check-in Date
**File:** `src/main/java/com/mycompany/Softwarepr1/Homepage.java`

The validation logic *does* exist, but depending on how the `JSpinner` (the date picker) captures the time of day, `checkOutDate.before(checkInDate)` can behave unexpectedly. Because it compares exact milliseconds instead of just the calendar days, a check-out date on the same day (but an earlier hour) might pass or fail incorrectly.

```java
// Around line 2746:
java.util.Date checkInDate = (java.util.Date) jSpinner2.getValue();
java.util.Date checkOutDate = (java.util.Date) jSpinner1.getValue();

// BUG: This compares exact Date/Time objects. If the time component differs, 
// the date validation might allow invalid day combinations.
if (checkOutDate.before(checkInDate) || checkOutDate.equals(checkInDate)) {
    JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.", 
            "Invalid Timing", JOptionPane.ERROR_MESSAGE);
    return;
}
```

## 3. Inaccurate Payment Error Message ("Cannot be all zeros")
**File:** `src/main/java/com/mycompany/Softwarepr1/Homepage.java`

The application combines two different validation checks (length/format AND checking for all zeros) into a single `if` statement with an `||` (OR) operator. Because of this, if you enter a card with an invalid length (e.g., 5 digits), it still shows the "all zeros" message.

```java
// Around line 2052:
// BUG: If the card is NOT 13-19 digits, it triggers the same error message as if it was all zeros.
if (!cardNumber.matches("^[0-9]{13,19}$") || cardNumber.matches("^0+$")) {
    JOptionPane.showMessageDialog(this, "Please enter a valid credit card number. It cannot be all zeros.");
    return;
}

// Around line 2056:
if (!cvv.matches("^[0-9]{3,4}$") || cvv.matches("^0+$")) {
    JOptionPane.showMessageDialog(this, "Please enter a valid CVV. It cannot be all zeros.");
    return;
}
```
