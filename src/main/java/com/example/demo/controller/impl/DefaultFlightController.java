package com.example.demo.controller.impl;

import com.example.demo.controller.FlightController;
import com.example.demo.dto.response.FlightStatus;
import com.example.demo.orchestrator.FlightOrchestrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/flight")
public class DefaultFlightController implements FlightController {
    private FlightOrchestrator orchestrator;

    @Override
    @GetMapping(value = "/status/flightNumber/{flightNumber}/travelDate/{travelDate}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightStatus> getFlightStatus(
            @PathVariable String flightNumber, @PathVariable LocalDate travelDate) {
        log.trace("Get request received for flight status with flight number {} and travel date {}", flightNumber, travelDate);
        return orchestrator.getFlightStatus(flightNumber, travelDate);
    }
}
