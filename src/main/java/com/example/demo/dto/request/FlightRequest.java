package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FlightRequest {
    private String flightNumber;
    private String departureAirportCode;
    private String arrivalAirportCode;
}
