package com.example.demo.orchestrator;

import com.example.demo.dto.response.FlightStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface FlightOrchestrator {
    ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate);
}
