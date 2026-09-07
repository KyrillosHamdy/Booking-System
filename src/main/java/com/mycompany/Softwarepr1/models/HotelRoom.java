package com.mycompany.Softwarepr1.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents a single bookable room inside a hotel.
 */
@Entity
@Table(name = "HotelRooms")
public class HotelRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "room_name")
    private String roomName;       // e.g. "Standard Room", "Deluxe Suite"
    
    @Column(name = "price_per_night")
    private double pricePerNight;  // in EGP
    
    @Column(name = "description")
    private String description;    // short marketing text shown on the card

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public HotelRoom() {}

    public HotelRoom(String roomName, double pricePerNight, String description) {
        this.roomName = roomName;
        this.pricePerNight = pricePerNight;
        this.description = description;
    }

    // Getters and setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    /** Convenience display string, e.g. "Deluxe Room – EGP 4,800/night" */
    @Override
    public String toString() {
        return roomName + " – EGP " + String.format("%,.0f", pricePerNight) + "/night";
    }
}
