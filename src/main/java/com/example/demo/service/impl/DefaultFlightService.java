package com.example.demo.service.impl;

import com.example.demo.entity.Flight;
import com.example.demo.exception.impl.FlightAlreadyExistsException;
import com.example.demo.exception.impl.FlightNotFoundException;
import com.example.demo.repository.FlightRepository;
import com.example.demo.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class DefaultFlightService implements FlightService {
    private FlightRepository repository;

    @Override
    public void addFlight(Flight flight) throws FlightAlreadyExistsException {
        if (repository.findById(flight.getFlightNumber()).isPresent())
            throw new FlightAlreadyExistsException(
                    "Can't add flight with flight number "
                            + flight.getFlightNumber()
                            + " as it already exists. Use update method instead.");

        repository.save(flight);
    }

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) throws FlightNotFoundException {
        var flight = repository.getFlightByFlightNumber(flightNumber);
        if (Objects.isNull(flight)) {
            throw new FlightNotFoundException("No flight found with flight number " + flightNumber);
        }

        return flight;
    }
}
