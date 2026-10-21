package org.karthik.propbookingsys.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    User user;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "prop_id",nullable = false)
    Properties property;
    @Column(nullable = false)
    int guests;
    @Column(nullable = false)
    LocalDateTime check_in;
    @Column(nullable = false)
    LocalDateTime check_out;
    @Column(nullable = false)
    BigDecimal totalAmount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    BookingStatus bookingStatus;

    public enum BookingStatus{
        CONFIRMED,
        CHECKEDIN,
        CANCELLED,
        COMPLETED
    }
}
