package com.example.demo.service.impl;

import com.example.demo.entity.FlightSchedule;
import com.example.demo.exception.impl.FlightNotFoundException;
import com.example.demo.exception.impl.FlightScheduleAlreadyExistsException;
import com.example.demo.exception.impl.FlightScheduleNotFoundException;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.FlightScheduleRepository;
import com.example.demo.service.FlightScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
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
                    log.info("No flight schedule found for flight number {}", flightNumber);
                    throw new FlightNotFoundException("No flight exists with flight number: " + flightNumber);
                });

        return Optional.ofNullable(repository.getFlightScheduleByFlightAndDepartureDate(flight, departureDate))
                .orElseThrow(() -> {
                    log.info("No flight schedule found for flight number {} and departure date {}", flightNumber, departureDate);
                    throw new FlightScheduleNotFoundException("No flight scheduled for departure date " + departureDate);
                });
    }

    @Override
    public void addFlightSchedule(FlightSchedule flightSchedule) {
        if (Boolean.FALSE.equals(Objects.isNull(
                repository.getFlightScheduleByFlightAndDepartureDateAndDepartureTime(
                        flightSchedule.getFlight(),
                        LocalDate.from(flightSchedule.getDepartureDateTime()),
                        LocalTime.from(flightSchedule.getDepartureDateTime()))))) {
            throw new FlightScheduleAlreadyExistsException(
                    "Can't add flight schedule for flight number "
                            + flightSchedule.getFlight().getFlightNumber()
                            + " and departure datetime "
                            + flightSchedule.getDepartureDateTime()
                            + " again because it already exists.");
        }

        repository.save(flightSchedule);
    }
}
