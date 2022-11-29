package com.example.demo.orchestrator.impl;

import com.example.demo.dto.response.FlightStatus;
import com.example.demo.orchestrator.FlightOrchestrator;
import com.example.demo.service.FlightScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class DefaultFlightOrchestrator implements FlightOrchestrator {
    private FlightScheduleService service;

    @Override
    public ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate) {
        var flightSchedule = service.getFlightSchedule(flightNumber, travelDate);
        var flightStatus = FlightStatus.builder()
                .status(flightSchedule.getStatus())
                .arrivalAirportCode(flightSchedule.getFlight().getArrivalAirport().getCode())
                .arrivalDateTime(flightSchedule.getArrivalDateTime())
                .departureAirportCode(flightSchedule.getFlight().getDepartureAirport().getCode())
                .departureDateTime(flightSchedule.getDepartureDateTime())
                .build();
        return ResponseEntity.ok(flightStatus);
    }
}
