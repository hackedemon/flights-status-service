package com.example.demo.entity;

import com.example.demo.entity.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class FlightSchedule implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Future(message = "Departure can't be in past.")
    private LocalDate departureDate;

    @Future(message = "Arrival can't be in past.")
    private LocalDate arrivalDate;

    @NotNull(message = "Departure time can't be null.")
    private LocalTime departureTime;

    @NotNull(message = "Arrival time can't be null.")
    private LocalTime arrivalTime;

    @Getter
    @Setter
    @NotNull(message = "Status can't be null.")
    private Status status;

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDate = departureDateTime.toLocalDate();
        this.departureTime = departureDateTime.toLocalTime();
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDate = arrivalDateTime.toLocalDate();
        this.arrivalTime = arrivalDateTime.toLocalTime();
    }

    public LocalDateTime getDepartureDateTime() {
        return this.departureDate.atTime(this.departureTime);
    }

    public LocalDateTime getArrivalDateTime() {
        return this.arrivalDate.atTime(this.arrivalTime);
    }
}
