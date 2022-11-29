package com.example.demo.controller;

import com.example.demo.dto.response.FlightStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface FlightController {
    ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate);
}
