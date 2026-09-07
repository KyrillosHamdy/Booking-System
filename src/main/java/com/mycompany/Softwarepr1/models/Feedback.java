package com.mycompany.Softwarepr1.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Feedback")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "recommendation")
    private Integer recommendation;

    @Column(name = "room_quality")
    private Integer roomQuality;

    @Column(name = "booking_process")
    private Integer bookingProcess;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    public Feedback() {}

    // --- Getters and Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public Integer getRecommendation() { return recommendation; }
    public void setRecommendation(Integer recommendation) { this.recommendation = recommendation; }

    public Integer getRoomQuality() { return roomQuality; }
    public void setRoomQuality(Integer roomQuality) { this.roomQuality = roomQuality; }

    public Integer getBookingProcess() { return bookingProcess; }
    public void setBookingProcess(Integer bookingProcess) { this.bookingProcess = bookingProcess; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}
