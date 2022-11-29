package com.example.demo.service.impl;

import com.example.demo.entity.FlightSchedule;
import com.example.demo.exception.impl.FlightNotFoundException;
import com.example.demo.exception.impl.FlightScheduleNotFoundException;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.FlightScheduleRepository;
import com.example.demo.service.FlightScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultFlightScheduleService implements FlightScheduleService {
    private FlightScheduleRepository repository;
    private FlightRepository flightRepository;

    @Override
    public FlightSchedule getFlightSchedule(String flightNumber, LocalDate departureDate)
            throws FlightNotFoundException, FlightScheduleNotFoundException {
        var flight = Optional.ofNullable(flightRepository.getFlightByFlightNumber(flightNumber))
                .orElseThrow(() -> {
                    throw new FlightNotFoundException("No flight exists with flight number: " + flightNumber);
                });

        return Optional.ofNullable(repository.getFlightScheduleByFlightAndDepartureDate(flight, departureDate))
                .orElseThrow(() -> {
                    throw new FlightScheduleNotFoundException("No flight scheduled for departure date " + departureDate);
                });
    }
}
