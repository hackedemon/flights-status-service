package com.example.demo.controller.impl;

import com.example.demo.controller.FlightController;
import com.example.demo.dto.request.AirportRequest;
import com.example.demo.dto.request.FlightRequest;
import com.example.demo.dto.request.FlightScheduleRequest;
import com.example.demo.dto.response.FlightStatus;
import com.example.demo.orchestrator.FlightOrchestrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("v1/flight/")
public class DefaultFlightController implements FlightController {
    private FlightOrchestrator orchestrator;

    @Override
    @GetMapping(value = "v1/status/flightNumber/{flightNumber}/travelDate/{travelDate}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightStatus> getFlightStatus(
            @PathVariable String flightNumber, @PathVariable LocalDate travelDate) {
        log.trace("Get request received for flight status with flight number {} and travel date {}", flightNumber, travelDate);
        return orchestrator.getFlightStatus(flightNumber, travelDate);
    }

    @Override
    @PostMapping(value = "v1/airport")
    public ResponseEntity<Void> addAirport(@RequestBody AirportRequest airportRequest) {
        return orchestrator.addAirport(airportRequest);
    }

    @Override
    @PostMapping(value = "v1/flight")
    public ResponseEntity<Void> addFlight(FlightRequest flightRequest) {
        return orchestrator.addFlight(flightRequest);
    }

    @Override
    @PostMapping(value = "v1/flightSchedule")
    public ResponseEntity<Void> addFlightSchedule(FlightScheduleRequest flightScheduleRequest) {
        return orchestrator.addFlightSchedule(flightScheduleRequest);
    }

    @Override
    @PatchMapping(value = "v1/flightSchedule/flightNumber/{flightNumber}/departureDate/{departureDate}")
    public ResponseEntity<Void> updateFlightSchedule(
            @PathVariable String flightNumber, @PathVariable LocalDate departureDate, @RequestBody Map<String, Object> updatedValues) {
        return orchestrator.updateFlightSchedule(flightNumber, departureDate, updatedValues);
    }
}
