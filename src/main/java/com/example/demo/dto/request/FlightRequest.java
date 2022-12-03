package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightRequest {
    private String flightNumber;
    private String departureAirportCode;
    private String arrivalAirportCode;
}
