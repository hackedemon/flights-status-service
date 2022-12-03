package com.example.demo.service;

import com.example.demo.entity.FlightSchedule;
import com.example.demo.exception.impl.FlightNotFoundException;
import com.example.demo.exception.impl.FlightScheduleAlreadyExistsException;
import com.example.demo.exception.impl.FlightScheduleNotFoundException;

import java.time.LocalDate;

public interface FlightScheduleService {
    /**
     * Method to get FlightSchedule
     *
     * @param flightNumber  flight number of flight for which data needs to be found.
     * @param departureDate departure date of flight for which data needs to be found.
     * @return Single flight detail if flight is found.
     * @throws FlightNotFoundException         when flight is not found for flight number.
     * @throws FlightScheduleNotFoundException when flight schedule is not found for
     *                                         flight number and departure date.
     */
    FlightSchedule getFlightSchedule(String flightNumber, LocalDate departureDate)
            throws FlightNotFoundException, FlightScheduleNotFoundException;

    void addFlightSchedule(FlightSchedule flightSchedule) throws FlightScheduleAlreadyExistsException;

    void updateFlightSchedule(FlightSchedule flightSchedule) throws FlightScheduleNotFoundException;
}
