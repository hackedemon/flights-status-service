package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Departure airport is mandatory")
    @ManyToOne
    @JoinColumn(name = "departure_airport_code")
    private Airport departureAirport;

    @Setter
    @NotNull(message = "Arrival airport is mandatory.")
    @ManyToOne
    @JoinColumn(name = "arrival_airport_code")
    private Airport arrivalAirport;

    private final LocalDateTime lastUpdateDateTime;

    /**
     * Constructor to set lastUpdate value to now.
     */
    public Flight() {
        this.lastUpdateDateTime = LocalDateTime.now();
    }
}
