package com.example.demo.dto.response;

import com.example.demo.entity.enumeration.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class FlightStatus {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private String flightNumber;
    private Status status;
}
