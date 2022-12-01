package com.example.demo.orchestrator.impl;

import com.example.demo.dto.response.FlightStatus;
import com.example.demo.entity.FlightSchedule;
import com.example.demo.orchestrator.FlightOrchestrator;
import com.example.demo.service.FlightScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@Component
public class DefaultFlightOrchestrator implements FlightOrchestrator {
    private FlightScheduleService service;

    @Override
    public ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate) {
        log.trace("getFlightStatus start");
        var startTime = System.currentTimeMillis();

        var flightSchedule = service.getFlightSchedule(flightNumber, travelDate);
        var flightStatus = getFlightStatus(flightSchedule);

        log.info("Flight status successfully retreived for flight number {} and travel date {} in {} s", flightNumber, travelDate, System.currentTimeMillis() - startTime);
        log.trace("getFlightStatus end");
        return ResponseEntity.ok(flightStatus);
    }

    private static FlightStatus getFlightStatus(FlightSchedule flightSchedule) {
        return FlightStatus.builder()
                .status(flightSchedule.getStatus())
                .arrivalAirportCode(flightSchedule.getFlight().getArrivalAirport().getCode())
                .arrivalDateTime(flightSchedule.getArrivalDateTime())
                .departureAirportCode(flightSchedule.getFlight().getDepartureAirport().getCode())
                .departureDateTime(flightSchedule.getDepartureDateTime())
                .flightNumber(flightSchedule.getFlight().getFlightNumber())
                .build();
    }
}
