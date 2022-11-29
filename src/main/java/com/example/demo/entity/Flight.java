package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Flight entity to store flight related data.
 */
@Getter
@Entity
public class Flight implements Serializable {
    @Setter
    @Id
    @NotBlank(message = "Flight number can't be null")
    private String flightNumber;

    @Setter
    @ManyToOne
    @JoinColumn(name = "departure_airport_code")
    @NotNull(message = "Departure airport is mandatory")
    private Airport departureAirport;

    @Setter
    @ManyToOne
    @JoinColumn(name = "arrival_airport_code")
    @NotNull(message = "Arrival airport is mandatory.")
    private Airport arrivalAirport;

    private final LocalDateTime lastUpdateDateTime;

    /**
     * Constructor to set lastUpdate value to now.
     */
    public Flight() {
        this.lastUpdateDateTime = LocalDateTime.now();
    }
}
