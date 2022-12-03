package com.example.demo.orchestrator.impl;

import com.example.demo.dto.request.AirportRequest;
import com.example.demo.dto.request.FlightRequest;
import com.example.demo.dto.request.FlightScheduleRequest;
import com.example.demo.dto.response.FlightStatus;
import com.example.demo.entity.Airport;
import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightSchedule;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.exception.impl.AirportNotFoundException;
import com.example.demo.exception.impl.FlightNotFoundException;
import com.example.demo.orchestrator.FlightOrchestrator;
import com.example.demo.service.AirportService;
import com.example.demo.service.FlightScheduleService;
import com.example.demo.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Component
public class DefaultFlightOrchestrator implements FlightOrchestrator {
    private FlightService service;
    private FlightScheduleService flightScheduleService;
    private AirportService airportService;

    @Override
    public ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate) {
        log.trace("getFlightStatus start");
        var startTime = System.currentTimeMillis();

        var flightSchedule = flightScheduleService.getFlightSchedule(flightNumber, travelDate);
        var flightStatus = getFlightStatus(flightSchedule);

        log.info("Flight status successfully retreived for flight number {} and travel date {} in {} s",
                flightNumber, travelDate, System.currentTimeMillis() - startTime);
        log.trace("getFlightStatus end");
        return ResponseEntity.ok(flightStatus);
    }

    @Override
    public ResponseEntity<Void> addAirport(AirportRequest airportRequest) {
        log.trace("addAirport start");
        var startTime = System.currentTimeMillis();

        var airport = new Airport(airportRequest.getCode(), airportRequest.getName());
        airportService.addAirport(airport);

        log.info("Added airport with code {} and name {} successfully in {} s",
                airport.getCode(), airport.getName(), System.currentTimeMillis() - startTime);
        log.trace("addAirport end");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addFlight(FlightRequest flightRequest) {
        log.trace("addFlight start");
        var startTime = System.currentTimeMillis();

        // TODO: Move airport code to another method.
        Airport arrivalAirport;
        Airport departureAirport;
        try {
            arrivalAirport = airportService.getAirportByCode(flightRequest.getArrivalAirportCode());
            departureAirport = airportService.getAirportByCode(flightRequest.getDepartureAirportCode());
        } catch (AirportNotFoundException airportNotFoundException) {
            throw new InvalidRequestException(airportNotFoundException.getMessage());
        }

        var flight = new Flight();
        flight.setFlightNumber(flightRequest.getFlightNumber());
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);

        service.addFlight(flight);

        log.info("Added flight with flight number {} successfully in {} s",
                flight.getFlightNumber(), System.currentTimeMillis() - startTime);
        log.trace("addFlight end");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addFlightSchedule(FlightScheduleRequest flightScheduleRequest) {
        log.trace("addFlightSchedule start");
        var startTime = System.currentTimeMillis();

        Flight flight;
        try {
            flight = service.getFlightByFlightNumber(flightScheduleRequest.getFlightNumber());
        } catch (FlightNotFoundException flightNotFoundException) {
            throw new InvalidRequestException(flightNotFoundException.getMessage());
        }

        var flightSchedule = new FlightSchedule();
        flightSchedule.setFlight(flight);
        flightSchedule.setArrivalDateTime(flightScheduleRequest.getArrivalDateTime());
        flightSchedule.setDepartureDateTime(flightScheduleRequest.getDepartureDateTime());
        flightSchedule.setStatus(flightScheduleRequest.getStatus());
        flightScheduleService.addFlightSchedule(flightSchedule);

        log.info("Added flight schedule for flight number {} successfully in {} s",
                flightScheduleRequest.getFlightNumber(), System.currentTimeMillis() - startTime);
        log.trace("addFlightSchedule end");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateFlightSchedule(
            String flightNumber, LocalDate departureDate, Map<String, Object> updatedValues) {
        var flightSchedule = flightScheduleService.getFlightSchedule(flightNumber, departureDate);

        // Removing flightNumber as it should not be updated.
        updatedValues.remove("flightNumber");

        // TODO: Move this to separate method.
        var flightScheduleRequest = new FlightScheduleRequest();
        var db = new DataBinder(flightScheduleRequest);
        var pvs = new MutablePropertyValues();
        pvs.addPropertyValues(updatedValues);
        db.bind(pvs);

        if (Boolean.FALSE.equals(Objects.isNull(flightScheduleRequest.getArrivalDateTime())))
            flightSchedule.setArrivalDateTime(flightScheduleRequest.getArrivalDateTime());
        if (Boolean.FALSE.equals(Objects.isNull(flightScheduleRequest.getDepartureDateTime())))
            flightSchedule.setDepartureDateTime(flightScheduleRequest.getDepartureDateTime());
        if (Boolean.FALSE.equals(Objects.isNull(flightScheduleRequest.getStatus())))
            flightSchedule.setStatus(flightScheduleRequest.getStatus());

        flightScheduleService.updateFlightSchedule(flightSchedule);

        return ResponseEntity.ok().build();
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
