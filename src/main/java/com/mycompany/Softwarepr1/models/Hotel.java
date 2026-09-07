package com.mycompany.Softwarepr1.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hotels")
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "destination_country")
    private String destinationCountry;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "star_rating")
    private int starRating; 
    
    @Column(name = "property_type")
    private String propertyType;
    
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HotelRoom> rooms;

    public Hotel() {
        this.rooms = new ArrayList<>();
    }

    public Hotel(String name, String destinationCountry, String city, int starRating) {
        this.name = name;
        this.destinationCountry = destinationCountry;
        this.city = city;
        this.starRating = starRating;
        this.propertyType = "Hotel";  // Default to 'Hotel'
        this.rooms = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    // Add a room to this hotel.
    public void addRoom(HotelRoom room) {
        room.setHotel(this);
        this.rooms.add(room);
    }

    /**
     * Find a room by its exact name (case-insensitive).
     * Returns {@code null} if not found.
     */
    public HotelRoom findRoom(String roomName) {
        for (HotelRoom r : rooms) {
            if (r.getRoomName().equalsIgnoreCase(roomName)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Returns the four Hotels that appear in the "Hotels" section
     * of the Homepage.
     */
    public static Hotel[] getHotelCatalogue() {

        // ----- Hotel 1 – Apparthotel Sonnenhof -----
        Hotel sonnenhof = new Hotel("Apparthotel Sonnenhof", "Germany", "Dresden", 4);
        sonnenhof.addRoom(new HotelRoom("Standard Room",   3_500,
                "Comfortable room with Wi-Fi, AC and city view."));
        sonnenhof.addRoom(new HotelRoom("Deluxe Room",     5_500,
                "Spacious room with premium furnishings and breakfast included."));
        sonnenhof.addRoom(new HotelRoom("Junior Suite",    8_200,
                "Elegant suite with separate lounge and panoramic windows."));

        // ----- Hotel 2 – Hilton Plaza -----
        Hotel hilton = new Hotel("Hilton Plaza Hotel", "Egypt", "Cairo", 5);
        hilton.addRoom(new HotelRoom("Standard Room",   4_200,
                "Classic Hilton comfort with all modern amenities."));
        hilton.addRoom(new HotelRoom("Deluxe Room",     6_800,
                "Premium bedding, Nile view and complimentary minibar."));
        hilton.addRoom(new HotelRoom("Executive Suite", 12_000,
                "Full executive lounge access, butler service and jacuzzi."));

        // ----- Hotel 3 – Steigenberger Pyramids -----
        Hotel steigenberger = new Hotel("Steigenberger Pyramids", "Egypt", "Cairo", 5);
        steigenberger.addRoom(new HotelRoom("Standard Room",       3_800,
                "Stylish room with pyramid-inspired décor and garden view."));
        steigenberger.addRoom(new HotelRoom("Deluxe Room",         6_200,
                "Upgraded furnishings, direct pyramid view and spa access."));
        steigenberger.addRoom(new HotelRoom("Presidential Suite",  15_000,
                "The ultimate luxury experience with panoramic pyramid panorama."));

        // ----- Hotel 4 – Pexels Boutique -----
        Hotel pexelsBoutique = new Hotel("Pexels Boutique Resort", "Indonesia", "Bali", 5);
        pexelsBoutique.addRoom(new HotelRoom("Garden Villa",    4_500,
                "Private villa surrounded by lush tropical gardens."));
        pexelsBoutique.addRoom(new HotelRoom("Ocean Bungalow",  7_500,
                "Overwater bungalow with direct ocean access and sunset views."));
        pexelsBoutique.addRoom(new HotelRoom("Royal Suite",     13_500,
                "Exclusive suite with private pool, butler and beach access."));
        
        return new Hotel[]{ sonnenhof, hilton, steigenberger, pexelsBoutique };
    }

    /**
     * Returns the four Suite rooms that appear in the "Suites" section
     * of the Homepage.  They are modelled as standalone "hotels" whose
     * only product is the featured suite.
     */
    public static Hotel[] getSuiteCatalogue() {

        Hotel oceanView = new Hotel("Luxury Suites Collection", "UAE", "Dubai", 5);
        oceanView.setPropertyType("Suite");
        oceanView.addRoom(new HotelRoom("Ocean View Suite",8_500,
                "Breathtaking ocean panorama, king bed, private terrace."));

        Hotel executive = new Hotel("Grand Executive Towers", "France", "Paris", 5);
        executive.setPropertyType("Suite");
        executive.addRoom(new HotelRoom("Executive Suite",      7_200,
                "Contemporary décor, city skyline view and lounge access."));

        Hotel panoramic = new Hotel("Skyline Residences", "UAE", "Dubai", 5);
        panoramic.setPropertyType("Suite");
        panoramic.addRoom(new HotelRoom("Panoramic Skyline Suite", 12_500,
                "360° skyline view, jacuzzi, butler and VIP amenities."));

        Hotel balcony = new Hotel("Garden Palace Hotel", "Spain", "Barcelona", 5);
        balcony.setPropertyType("Suite");
        balcony.addRoom(new HotelRoom("Grand Balcony Suite",    9_800,
                "Wrap-around balcony, dining area and premium spa access."));

        return new Hotel[]{ oceanView, executive, panoramic, balcony };
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }
    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getStarRating() {
        return starRating;
    }
    public void setStarRating(int starRating) {
        this.starRating = Math.max(1, Math.min(5, starRating));
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    /** Cheapest room price – used as the "Starting from …" value. */
    public double getStartingPrice() {
        return rooms.stream()
                .mapToDouble(HotelRoom::getPricePerNight)
                .min()
                .orElse(0);
    }

    @Override
    public String toString() {
        return name + " (" + city + ", " + destinationCountry + ")";
    }
}
