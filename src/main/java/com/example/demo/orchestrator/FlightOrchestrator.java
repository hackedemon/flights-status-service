package com.example.demo.orchestrator;

import com.example.demo.dto.request.AirportRequest;
import com.example.demo.dto.request.FlightRequest;
import com.example.demo.dto.request.FlightScheduleRequest;
import com.example.demo.dto.response.FlightStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

public interface FlightOrchestrator {
    ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate);

    ResponseEntity<Void> addAirport(AirportRequest airportRequest);

    ResponseEntity<Void> addFlight(FlightRequest flightRequest);

    ResponseEntity<Void> addFlightSchedule(FlightScheduleRequest flightScheduleRequest);

    ResponseEntity<Void> updateFlightSchedule(String flightNumber, Map<String, Object> updatedValues);
}
