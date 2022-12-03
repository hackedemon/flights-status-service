package com.example.demo.service;

import com.example.demo.entity.Flight;
import com.example.demo.exception.impl.FlightAlreadyExistsException;
import com.example.demo.exception.impl.FlightNotFoundException;

public interface FlightService {
    void addFlight(Flight flight) throws FlightAlreadyExistsException;

    Flight getFlightByFlightNumber(String flightNumber) throws FlightNotFoundException;
}
