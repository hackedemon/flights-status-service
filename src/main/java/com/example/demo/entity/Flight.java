package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Flight entity to store flight related data.
 */
@Getter
@Entity
public class Flight {
    @Id
    @Setter
    private String flightNumber;

    @Setter
    @ManyToOne
    @JoinColumn(name = "departure_airport_code")
    private Airport departureAirport;

    @Setter
    @ManyToOne
    @JoinColumn(name = "arrival_airport_code")
    private Airport arrivalAirport;

    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDate = departureDateTime.toLocalDate();
        this.departureTime = departureDateTime.toLocalTime();
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDate = arrivalDateTime.toLocalDate();
        this.arrivalTime = arrivalDateTime.toLocalTime();
    }
}
